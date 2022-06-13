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




$(document).ready(function () {
$("#sidebar").mCustomScrollbar({
theme: "minimal"
});

$('#dismiss, .overlay').on('click', function () {
$('#sidebar').removeClass('active');
$('.overlay').removeClass('active');
});

$('#sidebarCollapse').on('click', function () {
$('#sidebar').addClass('active');
$('.overlay').addClass('active');
$('.collapse.in').toggleClass('in');
$('a[aria-expanded=true]').attr('aria-expanded', 'false');
});
});


$('select').selectpicker();


$(function () {
$('.selectpicker').selectpicker();
});

function mensagemDosagem(){
const mensagem = document.getElementById("mensagemDosagem");
mensagem.innerHTML = "EX: 10, 50, 500"
}
function retirarMensagemDosagem(){
const mensagem = document.getElementById("mensagemDosagem");
mensagem.innerHTML = ""
}
function mensagemUnidade(){
const mensagem = document.getElementById("mensagemUnidade");
mensagem.innerHTML = "EX: miligrama (mg), gramas (g) mililitro (ml), gotas e microgotas"
}
function retirarMensagemUnidade(){
const mensagem = document.getElementById("mensagemUnidade");
mensagem.innerHTML = ""
}

