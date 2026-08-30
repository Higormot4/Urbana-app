CREATE DATABASE IF NOT EXISTS urbana;
USE urbana;

CREATE TABLE IF NOT EXISTS expressoes_culturais (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    artista VARCHAR(100),
    descricao TEXT,
    imagem_url TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Dados iniciais de teste
INSERT INTO expressoes_culturais (nome, categoria, cidade, artista, descricao, imagem_url) VALUES
('OSGEMEOS', 'Grafite', 'São Paulo', 'Gustavo e Otávio Pandolfo', 'Grafite icônico na região central de São Paulo.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Osgemeos_Sao_Paulo.jpg/800px-Osgemeos_Sao_Paulo.jpg'),
('Arte urbana', 'Arte de Rua', 'São Paulo', 'Vários artistas', 'Mural coletivo no Beco do Batman.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Beco_do_Batman_-_Sao_Paulo.jpg/800px-Beco_do_Batman_-_Sao_Paulo.jpg'),
('Museu Guggenheim', 'Arquitetura Orgânica', 'Bilbao', 'Frank Gehry', 'Arquitetura inovadora com formas curvilíneas.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Guggenheim-Bilbao-01.jpg/800px-Guggenheim-Bilbao-01.jpg'),
('Escultura urbana', 'Escultura Urbana', 'São Paulo', 'Tomie Ohtake', 'Escultura em homenagem à imigração japonesa.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Monumento_80_anos_imigracao_japonesa.jpg/800px-Monumento_80_anos_imigracao_japonesa.jpg');
