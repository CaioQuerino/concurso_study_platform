import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { QuestaoService } from '@/services/questao/questoes.service';
import { Questao, Alternativa } from '@/interfaces/questao';
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
    IonIcon
} from '@ionic/angular/standalone';

@Component({
  selector: 'app-criar-questao',
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
    IonIcon
  ],
  templateUrl: './criar-questao.component.html',
  styleUrls: ['./criar-questao.component.scss']
})
export class CriarQuestaoComponent {
  questao: Questao = {
    enunciado: '',
    disciplinaNome: '',
    ano: new Date().getFullYear(),
    nivelDificuldade: 'MEDIO',
    alternativas: [
      { texto: '', correta: false },
      { texto: '', correta: false },
      { texto: '', correta: false },
      { texto: '', correta: false }
    ]
  };

  private service = inject(QuestaoService)
  private router = inject(Router)

  salvar() {
    const alternativasCorretas = this.questao.alternativas.filter(alt => alt.correta);
    
    if (alternativasCorretas.length !== 1) {
      alert('Selecione exatamente uma alternativa como correta!');
      return;
    }

    this.service.criar(this.questao).subscribe({
      next: () => {
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.error('Erro ao criar questão:', error);
        alert('Erro ao criar questão. Verifique o console para mais detalhes.');
      }
    });
  }

  formValido(): boolean {
    return this.questao.enunciado.trim() !== '' && 
           this.questao.disciplinaNome.trim() !== '' &&
           this.questao.alternativas.every(alt => alt.texto.trim() !== '');
  }
}