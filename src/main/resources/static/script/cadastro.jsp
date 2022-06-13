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

function checarEmail() {
    const error = document.getElementById('error-email');
    const email = document.getElementById('US_Email').value;

    const dominios = ['gmail', 'ifsp', 'yahoo', 'live', 'hotmail', 'bol','yopmail'];

    let gmail = email.includes("gmail");
    let yahoo = email.includes("yahoo");
    let ifsp = email.includes("ifsp");
    let hotmail = email.includes("hotmail");
    let outlook = email.includes("outlook");
    let yopmail = email.includes("yopmail");

    if ((gmail !== true) && (yahoo !== true) && (ifsp !== true)
        && (hotmail !== true) && (outlook !== true) && (yopmail !== true)) {

        error.innerHTML = "E-mail inválido";

    } else {
        error.innerHTML = "";
    }
}

function validaData() {
    let data = document.getElementById("US_DataNascimento").value; // pega o valor do input
    data = data.replace(/\//g, "-"); // substitui eventuais barras (ex. IE) "/" por hífen "-"
    let data_array = data.split("-"); // quebra a data em array
    let error = document.getElementById("error-date");

    // para o IE onde será inserido no formato dd/MM/yyyy
    if (data_array[0].length != 4) {
        data = data_array[2] + "-" + data_array[1] + "-" + data_array[0]; // remonto a data no formato yyyy/MM/dd
    }

    // comparo as datas e calculo a idade
    let hoje = new Date();
    let nasc = new Date(data);
    let idade = hoje.getFullYear() - nasc.getFullYear();
    let m = hoje.getMonth() - nasc.getMonth();
    if (m < 0 || (m === 0 && hoje.getDate() < nasc.getDate())) idade--;

    if (idade < 13) {
        error.innerHTML = "Menores de 13 não podem se cadastrar";
        return false;
    }

    if (idade >= 13 && idade <= 100) {
        error.innerHTML = "";
        return true;
    }

    // se for maior que 60 não vai acontecer nada!
    return false;
}



