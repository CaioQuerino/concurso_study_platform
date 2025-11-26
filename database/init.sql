-- Tabela de Usuários
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    perfil VARCHAR(10) CHECK (perfil IN ('ADMIN', 'USER')) DEFAULT 'USER',
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Concursos
CREATE TABLE concursos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    orgao VARCHAR(100),
    banca_organizadora VARCHAR(100),
    data_prova DATE,
    edital_url TEXT,
    status VARCHAR(20) CHECK (status IN ('ABERTO', 'EM_ANDAMENTO', 'FINALIZADO')) DEFAULT 'ABERTO'
);

-- Tabela de Disciplinas
CREATE TABLE disciplinas (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

-- Tabela de Questões
CREATE TABLE questoes (
    id BIGSERIAL PRIMARY KEY,
    enunciado TEXT NOT NULL,
    disciplina_id BIGINT,
    concurso_id BIGINT,
    ano INTEGER,
    nivel_dificuldade VARCHAR(10) CHECK (nivel_dificuldade IN ('FACIL', 'MEDIO', 'DIFICIL')),
    FOREIGN KEY (disciplina_id) REFERENCES disciplinas(id),
    FOREIGN KEY (concurso_id) REFERENCES concursos(id)
);

-- Tabela de Alternativas
CREATE TABLE alternativas (
    id BIGSERIAL PRIMARY KEY,
    questao_id BIGINT,
    texto TEXT NOT NULL,
    correta BOOLEAN DEFAULT FALSE,
    letra CHAR(1),
    FOREIGN KEY (questao_id) REFERENCES questoes(id)
);

-- Tabela de Simulados
CREATE TABLE simulados (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT,
    nome VARCHAR(200),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tempo_duracao INT, -- em minutos
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);