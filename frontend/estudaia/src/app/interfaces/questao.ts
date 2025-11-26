export interface Questao {
    id?: number;
    enunciado: string;
    alternativas: string[];
    alternativaCorreta: number;
    explicacao: string;
    dificuldade: 'Fácil' | 'Médio' | 'Difícil';
    disciplina: string;
}