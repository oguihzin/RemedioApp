# RemedioApp

Aplicação desktop em Java para controle pessoal de uso de medicamentos — registre seus remédios, horários e acompanhe se já tomou (ou não) cada dose do dia.

## 💊 Motivação

Projeto pessoal criado para resolver um problema simples: lembrar se já tomei ou não determinado remédio no horário certo, com histórico e (futuramente) lembretes automáticos via WhatsApp.

## 🚧 Status do projeto

**Em desenvolvimento.** Funcionalidades implementadas até o momento:

- [x] Setup do projeto (JavaFX + Maven)
- [x] Modelagem das entidades `Remedio` e `RegistroTomada`
- [x] Estrutura de conexão com banco de dados (SQLite)
- [ ] Criação e inicialização das tabelas
- [ ] CRUD de remédios
- [ ] Tela de acompanhamento diário (marcar tomado/não tomado)
- [ ] Histórico de tomadas
- [ ] Lembretes automáticos via WhatsApp (CallMeBot)

## 🛠️ Tecnologias

- **Java 21**
- **JavaFX** — interface gráfica
- **Maven** — gerenciamento de dependências e build
- **SQLite** (via driver `sqlite-jdbc`) — persistência local em arquivo único, sem necessidade de servidor
- **CallMeBot API** (planejado) — envio de lembretes via WhatsApp

## 📦 Estrutura de dados

**Remedio**
- id, nome, dosagem, horários do dia (ex: `08:00`, `20:00`)

**RegistroTomada**
- id, remédio associado, data, horário previsto, se foi tomado, horário real da marcação

## ▶️ Como rodar

Pré-requisitos: JDK 21 instalado e configurado.

```bash
git clone <url-do-repositorio>
cd RemedioApp
mvn clean javafx:run
```

## 📌 Roadmap

- Finalizar persistência (SQLite)
- Interface para cadastro e acompanhamento diário
- Notificações automáticas via WhatsApp quando chegar o horário do remédio

## 📄 Licença

Projeto pessoal, sem licença definida ainda.