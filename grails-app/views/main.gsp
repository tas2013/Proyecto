<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <title>Pugali</title>
  
  <link rel="stylesheet" href="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.css">
  <!-- jQuery and jQuery Mobile -->
  <script src="https://d10ajoocuyu32n.cloudfront.net/jquery-1.9.1.min.js"></script>
  <script src="https://d10ajoocuyu32n.cloudfront.net/mobile/1.3.1/jquery.mobile-1.3.1.min.js"></script>

<script>
  var servicios = servicios || {};
  var ui = ui || {};

  $(document).ready(function() {
    ui.registrarEventosBotones();
  });

  ui.registrarEventosBotones = function() {
    $("#btGuardarOrganizacion").click(function() {
      servicios.guardarOrganizacion($("#nombreCorto").val(), $("#nombreCompleto").val(), $("#fechaCreacion").val());
    });
  };

  servicios.guardarOrganizacion = function(nombreCorto, nombreCompleto, fechaCreacion) {
    $.ajax({
      type: "POST",
      dataType: "text",
      url: "organizations/save",
      data: { shortName: nombreCorto, completeName: nombreCompleto }
    }).done(function( msg ) {
        alert( "Organizaci&oacute;n guardada: " + msg );
      }).fail(function( jqXHR, textStatus ) {
        alert( "Organizaci&oacute;n no fue guardada: " + textStatus );
      });
  };
</script>
   
</head>
<body>
<!-- Home -->
<div data-role="page" id="page1">
    <div data-theme="a" data-role="header">
        <h3>
            Header
        </h3>
        <div data-role="navbar" data-iconpos="top">
            <ul>
                <li>
                    <a href="#page1" data-transition="fade" data-theme="" data-icon="" class="ui-btn-active ui-state-persist">
                        Organización
                    </a>
                </li>
                <li>
                    <a href="#page2" data-transition="fade" data-theme="" data-icon="">
                        Objetivos Negocio
                    </a>
                </li>
                <li>
                    <a href="#page3" data-transition="fade" data-theme="" data-icon="">
                        Colaboradores
                    </a>
                </li>
            </ul>
        </div>
      <div data-role="content">
        <div data-role="fieldcontain">
              <label for="nombreCorto">
                  Nombre Corto
              </label>
              <input name="" id="nombreCorto" placeholder="" value="" type="text">
          </div>
          <div data-role="fieldcontain">
              <label for="nombreCompleto">
                  Nombre Completo
              </label>
              <input name="" id="nombreCompleto" placeholder="" value="" type="text">
          </div>
          <div data-role="fieldcontain">
              <label for="fechaCreacion">
                  Fecha de Creación
              </label>
              <input name="" id="fechaCreacion" placeholder="" value="" type="text">
          </div>
          <div data-role="fieldcontain">
            <a id='btGuardarOrganizacion' data-role="button" href="#page1" class="ui-btn-left">
                Ingresar
            </a>
          </div>
      </div>
    </div>
</div>
</body>
</html>