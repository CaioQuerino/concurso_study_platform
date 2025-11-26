import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Questao } from '@/interfaces/questao';

@Injectable({ providedIn: 'root' })
export class QuestaoService {
  private apiUrl = 'http://localhost:8080/api/questoes';

  private http = inject(HttpClient)

  listar(): Observable<Questao[]> {
    return this.http.get<any>(this.apiUrl).pipe(
      map(response => response.data) // Extrai o data da ApiResponse
    );
  }

  buscarPorId(id: number): Observable<Questao> {
    return this.http.get<any>(`${this.apiUrl}/${id}`).pipe(
      map(response => response.data)
    );
  }

  buscarPorDisciplina(disciplinaId: number): Observable<Questao[]> {
    return this.http.get<any>(`${this.apiUrl}/disciplina/${disciplinaId}`).pipe(
      map(response => response.data)
    );
  }

  // Métodos para criar, atualizar e excluir (se implementados no backend)
  criar(questao: Questao): Observable<Questao> {
    return this.http.post<any>(this.apiUrl, questao).pipe(
      map(response => response.data)
    );
  }

  atualizar(id: number, questao: Questao): Observable<Questao> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, questao).pipe(
      map(response => response.data)
    );
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`).pipe(
      map(response => response.data)
    );
  }
}