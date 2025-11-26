import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { QuestaoService } from '@/services/questao/questoes.service';
import { Questao } from '@/interfaces/questao';
import { 
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    IonItem,
    IonLabel,
    IonTextarea,
    IonInput,
    IonList,
    IonListHeader,
    IonSelect,
    IonSelectOption,
    IonButton,
    IonBackButton,
    IonButtons,
    IonCheckbox,
    IonNote,
    IonIcon,
    IonSpinner
} from '@ionic/angular/standalone';

@Component({
  selector: 'app-editar-questao',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule, 
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    IonItem,
    IonLabel,
    IonTextarea,
    IonInput,
    IonList,
    IonListHeader,
    IonSelect,
    IonSelectOption,
    IonButton,
    IonBackButton,
    IonButtons,
    IonCheckbox,
    IonNote,
    IonIcon,
    IonSpinner
  ],
  templateUrl: './editar-questao.component.html',
  styleUrls: ['./editar-questao.component.scss']
})
export class EditarQuestaoComponent implements OnInit {
  questao!: Questao;
  id!: number;

  private service = inject(QuestaoService)
  private route = inject(ActivatedRoute)
  private router = inject(Router)

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.buscarPorId(this.id).subscribe({
      next: (q) => {
        this.questao = q;
        // Garantir que todas as alternativas tenham a propriedade correta
        if (this.questao.alternativas) {
          this.questao.alternativas = this.questao.alternativas.map(alt => ({
            ...alt,
            correta: alt.correta || false
          }));
        }
      },
      error: (error) => {
        console.error('Erro ao carregar questão:', error);
        alert('Erro ao carregar questão.');
      }
    });
  }

  atualizar() {
    // Verificar se exatamente uma alternativa está marcada como correta
    const alternativasCorretas = this.questao.alternativas.filter(alt => alt.correta);
    
    if (alternativasCorretas.length !== 1) {
      alert('Selecione exatamente uma alternativa como correta!');
      return;
    }

    this.service.atualizar(this.id, this.questao).subscribe({
      next: () => {
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('Erro ao atualizar questão:', error);
        alert('Erro ao atualizar questão.');
      }
    });
  }

  formValido(): boolean {
    return this.questao && 
           this.questao.enunciado.trim() !== '' && 
           this.questao.disciplinaNome.trim() !== '' &&
           this.questao.alternativas.every(alt => alt.texto.trim() !== '');
  }
}