import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
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
  IonButton
],
templateUrl: './criar-questao.component.html'
})
export class CriarQuestaoComponent {
  questao: Questao = {
    enunciado: '',
    alternativas: ['', '', '', ''],
    alternativaCorreta: 0,
    explicacao: '',
    dificuldade: 'Fácil',
    disciplina: ''
  };

  private service = inject(QuestaoService)
  private router = inject(Router)

  salvar() {
    this.service.criar(this.questao).subscribe(() => this.router.navigate(['/']));
  }
}