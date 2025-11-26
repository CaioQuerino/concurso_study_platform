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
  IonInput,
  IonTextarea,
  IonSelect,
  IonSelectOption,
  IonList,
  IonListHeader,
  IonButton
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
  IonButton
],
templateUrl: '/editar-questao.component.html'
})
export class EditarQuestaoComponent implements OnInit {
  questao!: Questao;
  id!: number;

  private service = inject(QuestaoService)
  private route = inject(ActivatedRoute)
  private router = inject(Router)

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.buscarPorId(this.id).subscribe((q) => (this.questao = q));
  }

  atualizar() {
    this.service.atualizar(this.id, this.questao).subscribe(() => {
    this.router.navigate(['/']);
    });
  }
}