// Validação do formulário de cadastro
document.addEventListener("DOMContentLoaded", function() {
    const formCadastro = document.querySelector(".form-cadastro");
    const nomeInput = document.getElementById("nome");
    const generoInput = document.getElementById("genero");
    const lancamentoInput = document.getElementById("lancamento");
    const sinopseInput = document.getElementById("sinopse");

    formCadastro.addEventListener("submit", function(event) {
        let valid = true;
        let messages = [];

        // Validação do nome
        if (nomeInput.value.trim() === "") {
            valid = false;
            messages.push("O nome do livro é obrigatório.");
        }

        // Validação do gênero
        if (generoInput.value.trim() === "") {
            valid = false;
            messages.push("O gênero do livro é obrigatório.");
        }

        // Validação do lançamento
        const lancamento = parseInt(lancamentoInput.value);
        if (isNaN(lancamento) || lancamento < 0 || lancamento > 9999) {
            valid = false;
            messages.push("O ano de lançamento deve ser um número entre 0 e 9999.");
        }

        // Validação da sinopse
        if (sinopseInput.value.trim() === "") {
            valid = false;
            messages.push("A sinopse do livro é obrigatória.");
        }

        if (!valid) {
            event.preventDefault(); // Evita o envio do formulário
            alert(messages.join("\n")); // Exibe mensagens de erro
        }
    });

    // Preenchimento automático
    const livros = []; // Aqui você deve inserir a lista de livros disponíveis, por exemplo, carregando do servidor.

    const pesquisaInput = document.getElementById("pesquisa");
    const resultadoBusca = document.createElement("div");
    resultadoBusca.classList.add("resultado-busca");
    pesquisaInput.parentNode.appendChild(resultadoBusca);

    pesquisaInput.addEventListener("input", function() {
        const valor = pesquisaInput.value.toLowerCase();
        resultadoBusca.innerHTML = ""; // Limpa resultados anteriores

        if (valor) {
            const livrosFiltrados = livros.filter(livro => livro.nome.toLowerCase().startsWith(valor));

            livrosFiltrados.forEach(livro => {
                const div = document.createElement("div");
                div.textContent = livro.nome;
                div.classList.add("item-busca");
                div.addEventListener("click", function() {
                    window.location.href = `/livros/detalhe/${livro.id}`;
                });
                resultadoBusca.appendChild(div);
            });

            if (livrosFiltrados.length === 0) {
                resultadoBusca.textContent = "Nenhum livro encontrado.";
            }
        }
    });
});
