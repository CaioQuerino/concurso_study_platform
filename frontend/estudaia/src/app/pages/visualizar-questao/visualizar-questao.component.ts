import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { QuestaoService } from '@/services/questao/questoes.service';
import { Questao } from '@/interfaces/questao';


@Component({
selector: 'app-visualizar-questao',
standalone: true,
imports: [CommonModule],
templateUrl: './visualizar-questao.component.html'
})
export class VisualizarQuestaoComponent implements OnInit {
  questao?: Questao;

  private route = inject(ActivatedRoute)
  private service = inject(QuestaoService)

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.buscarPorId(id).subscribe(q => this.questao = q);
  }
}