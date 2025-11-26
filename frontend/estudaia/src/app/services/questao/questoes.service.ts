import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Questao } from '@/interfaces/questao';
import { ApiResponse } from '@/interfaces/apiResponse';
import { environment } from 'src/environments/environment';


@Injectable({ providedIn: 'root' })
export class QuestaoService {
  private apiUrl = environment.apiUrl;
  private apiKey = environment.apiKey;

  private http = inject(HttpClient)

  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'X-API-Key': this.apiKey
    });
  }

  listar(): Observable<Questao[]> {
    return this.http.get<ApiResponse<Questao[]>>(this.apiUrl, { 
      headers: this.getHeaders() 
    }).pipe(
      map(response => response.data)
    );
  }

  buscarPorId(id: number): Observable<Questao> {
    return this.http.get<ApiResponse<Questao>>(`${this.apiUrl}/${id}`, { 
      headers: this.getHeaders() 
    }).pipe(
      map(response => response.data)
    );
  }

  buscarPorDisciplina(disciplinaId: number): Observable<Questao[]> {
    return this.http.get<ApiResponse<Questao[]>>(`${this.apiUrl}/disciplina/${disciplinaId}`, { 
      headers: this.getHeaders() 
    }).pipe(
      map(response => response.data)
    );
  }

  criar(questao: Questao): Observable<Questao> {
    const questaoDTO = {
      enunciado: questao.enunciado,
      disciplinaNome: questao.disciplinaNome,
      ano: questao.ano || new Date().getFullYear(),
      nivelDificuldade: questao.nivelDificuldade,
      alternativas: questao.alternativas
    };

    return this.http.post<ApiResponse<Questao>>(this.apiUrl, questaoDTO, { 
      headers: this.getHeaders() 
    }).pipe(
      map(response => response.data)
    );
  }

  atualizar(id: number, questao: Questao): Observable<Questao> {
    const questaoDTO = {
      enunciado: questao.enunciado,
      disciplinaNome: questao.disciplinaNome,
      ano: questao.ano,
      nivelDificuldade: questao.nivelDificuldade,
      alternativas: questao.alternativas
    };

    return this.http.put<ApiResponse<Questao>>(`${this.apiUrl}/${id}`, questaoDTO, { 
      headers: this.getHeaders() 
    }).pipe(
      map(response => response.data)
    );
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<ApiResponse<void>>(`${this.apiUrl}/${id}`, { 
      headers: this.getHeaders() 
    }).pipe(
      map(response => undefined)
    );
  }
}