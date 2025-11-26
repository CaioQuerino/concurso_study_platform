export interface Questao {
    id?: number;
    enunciado: string;
    disciplinaNome: string;
    concursoNome?: string;
    ano?: number;
    nivelDificuldade: string;
    alternativas: Alternativa[];
}

export interface Alternativa {
    id?: number;
    texto: string;
    correta: boolean;
}