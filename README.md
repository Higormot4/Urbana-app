# URBANA - Antropologia Urbana

## 📋 Sobre o Projeto

O **URBANA** é um aplicativo mobile desenvolvido como projeto acadêmico para a disciplina de Antropologia Urbana. O aplicativo funciona como um catálogo digital de expressões culturais encontradas em metrópoles, permitindo que usuários explorem e conheçam diferentes manifestações artísticas urbanas.

## 🎯 Objetivo

Criar uma plataforma que documente e apresente as diversas formas de expressão cultural presentes nas cidades, desde grafites até arquitetura orgânica, promovendo a valorização da arte urbana e da diversidade cultural.

## ✨ Funcionalidades

### 🔐 Autenticação

- Login com e-mail e senha
- Cadastro de novos usuários
- Validação de campos em tempo real
- Mensagens de erro personalizadas
- Persistência de sessão

### 📱 Catálogo

- Listagem de expressões culturais
- Filtros por categoria
- Cards com informações resumidas
- Imagens das obras/expressões
- Detalhes completos ao clicar

## 🎨 Categorias Disponíveis

| Categoria | Descrição |
|---|---|
| Grafite | Arte urbana em muros e paredes |
| Arte de Rua | Intervenções artísticas urbanas |
| Arquitetura Orgânica | Edificações com formas naturais |
| Escultura Urbana | Obras tridimensionais em espaços públicos |
| Performance Urbana | Manifestações artísticas ao vivo |

## 🛠️ Tecnologias Utilizadas

### Frontend (Android)

| Tecnologia | Versão | Descrição |
|---|---|---|
| Kotlin | 2.0.21 | Linguagem de programação |
| Jetpack Compose | 2024.11 | UI Toolkit moderno |
| Material 3 | - | Design System |
| Navigation Compose | 2.8.4 | Navegação entre telas |
| Firebase Auth | 33.6.0 | Autenticação de usuários |
| Retrofit | 2.11.0 | Consumo de API REST |
| Coil | 2.7.0 | Carregamento de imagens |

### Backend (API)

| Tecnologia | Versão | Descrição |
|---|---|---|
| Node.js | 20.x | Runtime JavaScript |
| Express | 4.18 | Framework web |
| MySQL | 8.0 | Banco de dados |
| CORS | 2.8 | Compartilhamento de recursos |

## 📁 Estrutura do Projeto

```
urbana-projeto/
│
├── android/                          # Aplicativo Android
│   ├── app/
│   │   ├── src/main/
│   │   │   ├── java/com/urbana/app/
│   │   │   │   ├── data/
│   │   │   │   │   ├── model/        # Modelos de dados
│   │   │   │   │   ├── remote/       # API e Retrofit
│   │   │   │   │   └── repository/   # Repositórios
│   │   │   │   ├── ui/
│   │   │   │   │   ├── screens/      # Telas do app
│   │   │   │   │   ├── components/   # Componentes reutilizáveis
│   │   │   │   │   └── theme/        # Tema e cores
│   │   │   │   ├── navigation/       # Navegação
│   │   │   │   └── viewmodel/        # ViewModels
│   │   │   └── res/
│   │   │       └── drawable/         # Imagens locais
│   │   └── build.gradle.kts
│   └── build.gradle.kts
│
├── backend/                          # API REST
│   ├── server.js                     # Servidor Express
│   ├── package.json                  # Dependências
│   └── .env                          # Variáveis de ambiente
│
└── README.md                         # Documentação
```

## 🚀 Como Executar

### Pré-requisitos

- Android Studio (versão mais recente)
- Node.js (versão 20 ou superior)
- MySQL (versão 8.0 ou superior)
- JDK 17 ou superior

### Passo 1: Configurar o Backend

1. Navegue até a pasta do backend:
   ```bash
   cd backend
   ```

2. Instale as dependências:
   ```bash
   npm install
   ```

3. Configure o banco de dados:
   ```sql
   CREATE DATABASE urbana;
   ```

4. Configure o arquivo `.env`:
   ```env
   DB_HOST=localhost
   DB_USER=root
   DB_PASSWORD=sua_senha
   DB_NAME=urbana
   PORT=3000
   ```

5. Inicie o servidor:
   ```bash
   npm start
   ```

   O servidor estará rodando em: `http://localhost:3000`

### Passo 2: Configurar o Android

1. Abra o Android Studio
2. Selecione **"Open Project"**
3. Navegue até a pasta `android/`
4. Aguarde a sincronização do Gradle

### Passo 3: Configurar o Firebase

1. Acesse o [Firebase Console](https://console.firebase.google.com/)
2. Crie um projeto chamado **"URBANA"**
3. Adicione um app Android com package `com.urbana.app`
4. Baixe o arquivo `google-services.json`
5. Cole o arquivo em `android/app/`
6. Ative a autenticação por e-mail/senha

### Passo 4: Executar o App

1. Conecte um dispositivo ou inicie um emulador
2. Clique em **Run** no Android Studio
3. Aguarde a compilação e instalação

## 📡 API Endpoints

### Expressões Culturais

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/expressoes` | Lista todas as expressões |
| GET | `/api/expressoes/:id` | Busca expressão por ID |
| POST | `/api/expressoes` | Cria nova expressão |
| PUT | `/api/expressoes/:id` | Atualiza expressão |
| DELETE | `/api/expressoes/:id` | Remove expressão |

### Categorias

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/api/categorias` | Lista todas as categorias |

### Exemplo de Resposta

```json
{
  "id": 1,
  "nome": "OSGEMEOS - Gigantes Urbanos",
  "categoria": "Grafite",
  "cidade": "São Paulo",
  "artista": "Otavio e Gustavo Pandolfo",
  "descricao": "Os irmãos Otavio e Gustavo Pandolfo...",
  "imagemUrl": "https://exemplo.com/imagem.jpg",
  "createdAt": "2024-01-01T10:00:00Z",
  "updatedAt": "2024-01-01T10:00:00Z"
}
```

## 🎨 Design e Identidade Visual

### Paleta de Cores

| Cor | Código | Uso |
|---|---|---|
| Preto/Carvão | `#1A1A1A` | Background principal |
| Branco | `#FFFFFF` | Textos e superfícies |
| Cinza | `#666666` | Textos secundários |
| Laranja | `#FF6B35` | Cor de destaque |

### Tipografia

- **Títulos:** Bold, 32sp
- **Subtítulos:** SemiBold, 22sp
- **Corpo:** Regular, 16sp
- **Legendas:** Regular, 14sp

### Componentes

- Cards com cantos arredondados (12dp)
- Chips para categorias
- Botões com altura de 50dp
- Ícones em campos de input

## 📱 Screens do App

| Tela | Descrição |
|---|---|
| Splash | Tela inicial com logo e animação |
| Login | Autenticação do usuário |
| Cadastro | Criação de nova conta |
| Home | Dashboard com categorias |
| Catálogo | Lista de expressões culturais |
| Detalhes | Informações completas |

## 🔒 Segurança

- Autenticação via Firebase (e-mail/senha)
- Validação de campos no frontend e backend
- Senhas criptografadas pelo Firebase
- Arquivos sensíveis ignorados no Git
- Variáveis de ambiente para credenciais

## 📝 Dados de Exemplo

O banco de dados inclui exemplos de expressões culturais:

- **OSGEMEOS** - Grafite - São Paulo
- **Beco do Batman** - Arte de Rua - São Paulo
- **Museu Guggenheim** - Arquitetura Orgânica - Bilbao
- **Escultura Trianon** - Escultura Urbana - São Paulo
- **Performance na Paulista** - Performance Urbana - São Paulo

## 🧪 Testes

### Testes Manuais

- [ ] **Cadastro:** Criar conta com validações
- [ ] **Login:** Entrar com credenciais válidas
- [ ] **Catálogo:** Navegar e filtrar por categoria
- [ ] **Detalhes:** Visualizar informações completas
- [ ] **Logout:** Sair da conta

### Validações Implementadas

- E-mail obrigatório e válido
- Senha com mínimo de 6 caracteres
- Confirmação de senha
- Nome obrigatório
- Tratamento de erros do Firebase
- Loading states
- Fallback para dados locais

---

**URBANA** - *A cidade também fala.*

Desenvolvido para o projeto acadêmico.
