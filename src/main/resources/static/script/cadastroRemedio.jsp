//Esta funcao se encarrega de trocar o idioma da p�gina
$(document).ready(function () {
        
        //id do elemento html = "#locales" (� um select)
        $("#locales").change(function () {
            
            //Variavel armazena o value do elemento "#locales"
            var selectedOption = $('#locales').val();
            
            // Verifica e seta o idioma escolhido
            if (selectedOption != '') {
                window.location.replace('?lang=' + selectedOption);
            }
        });
    });

//Funções que mostram ou escondem o formulário
const Modal = {
    open(){
        //Abrir modal
        //Adicionar a classe "active" ao modal
        document.querySelector('.modal-overlay').classList.add('active');
    },
    close(){
        //Fechar o modal
        //Remover a classe "active" do modal
        document.querySelector('.modal-overlay').classList.remove('active');
    }
}