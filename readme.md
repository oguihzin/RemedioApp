# RemedioApp

Aplicação desktop em Java para controle pessoal de uso de medicamentos — registre seus remédios, horários e acompanhe se já tomou (ou não) cada dose do dia.

## 💊 Motivação

Projeto pessoal criado para resolver um problema simples: lembrar se já tomei ou não determinado remédio no horário certo, com histórico e (futuramente) lembretes automáticos via WhatsApp.

## 🚧 Status do projeto

**Em desenvolvimento.** Funcionalidades implementadas até o momento:

- [x] Setup do projeto (JavaFX + Maven)
- [x] Modelagem das entidades `Remedio` e `RegistroTomada`
- [x] Banco de dados SQLite (criação de tabelas, conexão)
- [x] CRUD de remédios (cadastrar, listar, remover)
- [x] Geração automática dos registros do dia (um por remédio/horário)
- [x] Tela "Hoje" com checkbox para marcar remédio como tomado (com horário real registrado)
- [x] Validações de formulário e mensagens de erro na interface
- [x] Proteção contra remoção de remédio com registros do dia
- [x] Interface com abas (Hoje / Meus Remédios) e estilização CSS própria
- [ ] Histórico de dias anteriores
- [ ] Lembretes automáticos via WhatsApp (CallMeBot)

## 🛠️ Tecnologias

- **Java 21**
- **JavaFX** — interface gráfica, com estilização própria via CSS
- **Maven** — gerenciamento de dependências e build
- **SQLite** (via driver `sqlite-jdbc`) — persistência local em arquivo único, sem necessidade de servidor
- **CallMeBot API** (planejado) — envio de lembretes via WhatsApp

## 📦 Estrutura de dados

**Remedio**
- id, nome, dosagem, horários do dia (ex: `08:00`, `20:00`)

**RegistroTomada**
- id, remédio associado, data, horário previsto, se foi tomado, horário real da marcação

## 🏗️ Arquitetura

- `Remedio` / `RegistroTomada` — modelos de dados
- `DatabaseHelper` — conexão e criação das tabelas no SQLite
- `RemedioRepository` / `RegistroTomadaRepository` — acesso ao banco (CRUD)
- `RegistroService` — regra de negócio que gera os registros do dia automaticamente
- `RemedioController` / `HojeController` — controllers das telas (FXML)
- `main-view.fxml` — tela principal com abas, incluindo `remedio-view.fxml` e `hoje-view.fxml`

## ▶️ Como rodar

Pré-requisitos: JDK 21 instalado e configurado.

```bash
git clone <url-do-repositorio>
cd RemedioApp
mvn clean javafx:run
```

## 📌 Roadmap

- Histórico de dias anteriores (visualizar tomadas passadas)
- Notificações automáticas via WhatsApp quando chegar o horário do remédio (CallMeBot)

## 📄 Licença

Projeto pessoal, sem licença definida ainda.