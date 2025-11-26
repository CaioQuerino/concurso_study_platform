import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { QuestaoService } from '@/services/questao/questoes.service';
import { Questao } from '../../interfaces/questao';
import { 
    IonButton,
   IonButtons,
   IonContent,
   IonIcon,
   IonItem,
   IonLabel,
   IonList 
  } from '@ionic/angular/standalone';

@Component({
selector: 'app-listar-questoes',
standalone: true,
imports: [
  CommonModule, 
  RouterModule,
  IonIcon,
  IonButton,
  IonButtons,
  IonLabel,
  IonItem,
  IonList,
  IonContent
],
templateUrl: './listar-questoes.component.html'
})
export class ListarQuestoesComponent implements OnInit {
  questoes: Questao[] = [];

  private service = inject(QuestaoService)

  ngOnInit(): void {
    this.carregar();
  }

  carregar() {
    this.service.listar().subscribe((dados) => (this.questoes = dados));
  }

  excluir(id: number) {
    this.service.excluir(id).subscribe(() => this.carregar());
  }
}