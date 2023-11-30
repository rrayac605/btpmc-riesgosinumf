package mx.gob.imss.cit.pmc.riesgosinumf.constantes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;

import com.google.common.collect.ImmutableMap;

public class RiesgoSinUmfConstantes {
		
	/*** Claves (aseguradoDTO.cveEstadoRegistro) antes de actualizar ***/
	public static final int CVE_CORRECTO = 1;
	
	public static final int CVE_ERRONEO = 2;
	
	public static final int CVE_SUSCEPTIBLE = 4;
	
	/*** Claves (aseguradoDTO.cveEstadoRegistro) despues de actualizar ***/
	public static final int CVE_CORRECTO_OTRAS = 5;
	
	public static final int CVE_ERRONEO_OTRAS = 6;
	
	public static final int CVE_SUSCEPTIBLE_OTRAS = 8;
		
	/*** Descripciones (aseguradoDTO.desEstadoRegistro) a ser actualizadas ***/	
	public static final String DES_CORRECTO = "Correcto";
		
	public static final String DES_ERRONEO = "Erróneo";
	
	public static final String DES_SUSCEPTIBLE = "Susceptibles de ajuste";
	
	public static final String DES_CORRECTO_OTRAS = "Correctos otras delegaciones";
	
	public static final String DES_ERRONEO_OTRAS = "Erróneos otras delegaciones";
	
	public static final String DES_SUSCEPTIBLE_OTRAS = "Susceptibles de ajuste otras delegaciones";
		
	/*** Auditorias ***/
	public static final String FORMATO_FECHA_HORA_ISO = "ddMMyyyy HH:mm:ss";
	
	public static final String USUARIO = "Sistema PMC";
		
	public static final String ACCION = "Modificación";
		
	public static final int ORDEN_EJECUCION_1 = 1;
	
	public static final String DES_SITUACION_REGISTRO_PENDIENTE = "Pendiente de Aprobar";
			
	public static final int ORDEN_EJECUCION_2 = 2;
	
	public static final String DES_SITUACION_REGISTRO_APROBADO = "Aprobado";
		
	public static final String DESCRIPCION = "Asignación del riesgo a la OOAD y subdelegación del registro patronal.";
	
	/*** Chunk size ***/
	public static final Integer CHUNK_SIZE = 100;
	
	/*** Parametros ***/
	public static final String FECHA_EJECUCION = "execution_date";
	
	public static final String PRIMER_DIA = "01";
	
	public static final String ISO_TIMEZONE = "UTC";
	
	public static final String HORA_INICIAL = " 00:00:00";
	
	public static final String HORA_FINAL = " 23:59:59";
	
	public static final String CERO = "0";
	
	public static final String VACIO = "";
	
	public static final String COUNT = "count"; 
	
	/*** Ordenamiento ***/
	public static final Map<String, Sort.Direction> ORDENAMIENTO = ImmutableMap.of("_id", Sort.Direction.ASC);
	
	/*** Correo electronico ***/
	public static final String RUTA_IMGS = "/static/images/";
	
	public static final String ENCABEZADO_IMG = "header.png";
	
	public static final String PIE_IMG = "footer.png";
	
	public static final String CORREO_TEMPLATE = "RIESGO_SIN_UMF_TEMPLATE";
	
	public static final String FECHA_HORA = "${fechaHora}";
	
	public static final String TOTALES_ANTES = "${registrosAntes}";
	
	public static final String TOTALES_ACTUALIZADOS = "${registrosSinUmf}";
	
	public static final String TOTALES_DESPUES = "${registrosDespues}";
	
	public static final String LISTA_ASIGNACION = "${listaAsignacion}"; 
		
	public static final String FORMATO_FECHA_HORA = "dd-MM-yyyy HH:mm";
	
	public static final String[] REGISTROS_ANTES = {"Total de registros sin UMF de adscripci&oacute;n que se obtuvieron: ",
			"Total de registros que se asignaron a la OOAD y subdelegaci&oacute;n del registro patronal: ",
			"Total de registros que no pudieron ser asignados a la OOAD y subdelegaci&oacute;n del registro patronal: "};
	
	public static final String[] REGISTROS_ACTUALIZADOS = {"Total de registros Correcto a Correcto otras delegaciones: ",
			"Total de registros Err&oacute;neo a Err&oacute;neo otras delegaciones: ",
			"Total de registros Susceptibles de ajuste a Susceptible de ajuste otras delegaciones: "};
	
	public static final String[] REGISTROS_DESPUES = {"Total de registros Correcto otras delegaciones: ",
			"Total de registros Err&oacute;neo otras delegaciones: ",
			"Total de registros Susceptible de ajuste otras delegaciones: "};
	
	/*** Mapas para listado de asignación de riesgos ***/
	public static final Map<Integer, List<Integer>> CVES_DEL_SUBDEL = ImmutableMap.<Integer, List<Integer>>builder()
            .put(1, Arrays.asList(1, 1, 19))
            .put(2, Arrays.asList(2, 1, 2, 3, 4, 5, 59))
    		.put(3, Arrays.asList(3, 1, 8))
            .put(4, Arrays.asList(4, 1, 4))
            .put(5, Arrays.asList(8, 1, 3, 5, 8, 10, 22, 60))
            .put(6, Arrays.asList(6, 1, 3, 7))
            .put(7, Arrays.asList(5, 3, 9, 11, 12, 17, 23))
            .put(8, Arrays.asList(7, 1, 2))
            .put(9, Arrays.asList(39, 11, 16, 54, 56, 57))
            .put(10, Arrays.asList(40, 1, 6, 11, 54, 58))
            .put(11, Arrays.asList(10, 1, 13))
            .put(12, Arrays.asList(12, 1, 2, 3, 13))
            .put(13, Arrays.asList(11, 1, 5, 8, 14, 17))
            .put(14, Arrays.asList(13, 1, 5, 7, 10))
            .put(15, Arrays.asList(14, 12, 15, 22, 38, 39, 40, 50))
            .put(16, Arrays.asList(17, 3, 9, 13, 17, 27))
            .put(17, Arrays.asList(16, 1, 5))
            .put(18, Arrays.asList(20, 6, 8, 31, 32, 33, 34))
            .put(19, Arrays.asList(18, 1, 11, 15))
            .put(20, Arrays.asList(19, 1))
            .put(21, Arrays.asList(21, 2, 3, 4, 53))
            .put(22, Arrays.asList(22, 1, 5, 6, 8, 22))
            .put(23, Arrays.asList(24, 1, 2, 7))
            .put(24, Arrays.asList(23, 1, 3))
            .put(25, Arrays.asList(26, 1, 3, 4, 5))
            .put(26, Arrays.asList(25, 1, 3, 5, 60))
            .put(27, Arrays.asList(27, 1, 3, 7, 10, 13, 51, 57, 70))
            .put(28, Arrays.asList(29, 1, 4, 10, 13, 18, 19))
            .put(29, Arrays.asList(28, 1, 2))
            .put(30, Arrays.asList(15, 6, 54, 80))
            .put(31, Arrays.asList(30, 1))
            .put(32, Arrays.asList(31, 2, 7, 9, 12, 25))
            .put(33, Arrays.asList(32, 2, 3, 38, 45))
            .put(34, Arrays.asList(33, 1, 33))
            .put(35, Arrays.asList(34, 1, 9))
            .build();
	
	public static final Map<Integer, String> DESCRIPCION_DELEGACION = ImmutableMap.<Integer, String>builder()
    		.put(1, "AGUASCALIENTES")
    		.put(2, "BAJA CALIFORNIA NORTE")
            .put(3, "BAJA CALIFORNIA SUR")
            .put(4, "CAMPECHE")
            .put(5, "CHIHUAHUA")
            .put(6, "COLIMA")
            .put(7, "COAHUILA")
            .put(8, "CHIAPAS")
            .put(9, "DISTRITO FEDERAL NORTE")
            .put(10, "DISTRITO FEDERAL SUR")
            .put(11, "DURANGO")
            .put(12, "GUERRERO")
            .put(13, "GUANAJUATO")
            .put(14, "HIDALGO")
            .put(15, "JALISCO")
            .put(16, "MICHOACAN")
            .put(17, "ESTADO DE MEXICO PONIENTE")
            .put(18, "NUEVO LEON")
            .put(19, "MORELOS")
            .put(20, "NAYARIT")
            .put(21, "OAXACA")
            .put(22, "PUEBLA")
            .put(23, "QUINTANA ROO")
            .put(24, "QUERETARO")
            .put(25, "SINALOA")
            .put(26, "SAN LUIS POTOSI")
            .put(27, "SONORA")
            .put(28, "TAMAULIPAS")
            .put(29, "TABASCO")
            .put(30, "ESTADO DE MEXICO ORIENTE")
            .put(31, "TLAXCALA")
            .put(32, "VERACRUZ NORTE")
            .put(33, "VERACRUZ SUR")
            .put(34, "YUCATAN")
            .put(35, "ZACATECAS")
            .build();
	
	public static final Map<Integer, List<String>> DESCRIPCION_SUBDELS = ImmutableMap.<Integer, List<String>>builder()			
            .put(1, Arrays.asList("AGUASCALIENTES NORTE", "AGUASCALIENTES SUR"))
            .put(2, Arrays.asList("MEXICALI", "TECATE", "ENSENADA", "SAN LUIS R&Iacute;O COLORADO", "TIJUANA", "LOS ANGELES"))
    		.put(3, Arrays.asList("LA PAZ", "CABO SAN LUCAS"))
            .put(4, Arrays.asList("CIUDAD DE CAMPECHE", "CIUDAD DEL CARMEN"))
            .put(5, Arrays.asList("CHIHUAHUA", "CUAUHT&Eacute;MOC", "DELICIAS", "NUEVO CASAS GRANDES", "JU&Aacute;REZ 1", "HIDALGO DEL PARRAL", "JU&Aacute;REZ 2"))
            .put(6, Arrays.asList("COLIMA", "MANZANILLO", "TECOM&Aacute;N"))
            .put(7, Arrays.asList("SALTILLO", "TORRE&Oacute;N", "CIUDAD ACUÑA", "PIEDRAS NEGRAS", "MONCLOVA", "SABINAS"))
            .put(8, Arrays.asList("TUXTLA", "TAPACHULA"))
            .put(9, Arrays.asList("1 MAGDALENA DE LAS SALINAS", "3 POLANCO", "2 SANTA MAR&Iacute;A LA RIBERA", "4 GUERRERO", "5 CENTRO"))
            .put(10, Arrays.asList("8 SAN &Aacute;NGEL", "7 DEL VALLE", "9 SANTA ANITA", "10 CHURUBUSCO", "6 PIEDAD NARVARTE"))
            .put(11, Arrays.asList("DURANGO", "G&Oacute;MEZ PALACIO"))
            .put(12, Arrays.asList("CHILPANCINGO", "ACAPULCO", "IGUALA", "ZIHUATANEJO"))
            .put(13, Arrays.asList("GUANAJUATO", "IRAPUATO", "CELAYA", "SALAMANCA", "LE&Oacute;N"))
            .put(14, Arrays.asList("PACHUCA", "TULANCINGO", "CIUDAD SAHAG&Uacute;N", "TULA DE ALLENDE"))
            .put(15, Arrays.asList("TEPATITL&Aacute;N DE MORELOS", "OCOTL&Aacute;N", "CIUDAD GUZM&Aacute;N", "HIDALGO", "LIBERTAD REFORMA", "JU&Aacute;REZ", "PUERTO VALLARTA"))
            .put(16, Arrays.asList("MORELIA", "URUAPAN", "ZAMORA", "ZIT&Aacute;CUARO", "L&Aacute;ZARO C&Aacute;RDENAS"))
            .put(17, Arrays.asList("TOLUCA", "NAUCALPAN"))
            .put(18, Arrays.asList("MONTEMORELOS", "APODACA", "2 NORESTE", "1 NOROESTE", "4 SURESTE", "3 SUROESTE"))
            .put(19, Arrays.asList("CUERNAVACA", "CUAUTLA", "ZACATEPEC"))
            .put(20, Arrays.asList("TEPIC"))
            .put(21, Arrays.asList("OAXACA", "SALINA CRUZ", "SAN JUAN BAUTISTA TUXTEPEC", "SANTA MAR&Iacute;A HUATULCO"))
            .put(22, Arrays.asList("PUEBLA NORTE", "TEZI&Uacute;TLAN", "TEHUAC&Aacute;N", "IZ&Uacute;CAR DE MATAMOROS", "PUEBLA SUR"))
            .put(23, Arrays.asList("CHETUMAL", "PLAYA DEL CARMEN", "CANC&Uacute;N"))
            .put(24, Arrays.asList("QUER&Eacute;TARO", "SAN JUAN DEL R&Iacute;O"))
            .put(25, Arrays.asList("CULIAC&Aacute;N", "LOS MOCHIS", "GUASAVE", "MAZATLAN"))
            .put(26, Arrays.asList("ORIENTE", "MATEHUALA", "CIUDAD VALLES", "PONIENTE"))
            .put(27, Arrays.asList("HERMOSILLO", "GUAYMAS", "NAVOJOA", "NOGALES", "CABORCA", "AGUA PRIETA", "NACOZARI DE GARCIA", "CIUDAD OBREG&Oacute;N"))
            .put(28, Arrays.asList("CIUDAD VICTORIA", "REYNOSA", "TAMPICO", "CIUDAD MANTE", "NUEVO LAREDO", "MATAMOROS"))
            .put(29, Arrays.asList("VILLA HERMOSA", "C&Aacute;RDENAS"))
            .put(30, Arrays.asList("TLALNEPANTLA DE BAZ", "ECATEPEC", "LOS REYES-LA PAZ"))
            .put(31, Arrays.asList("TLAXCALA"))
            .put(32, Arrays.asList("XALAPA", "POZA RICA DE HIDALGO", "MART&Iacute;NEZ DE LA TORRE", "VERACRUZ", "LERDO DE TEJADA"))
            .put(33, Arrays.asList("C&Oacute;RDOBA", "ORIZABA", "COSAMALOAPAN", "COATZACOALCOS"))
            .put(34, Arrays.asList("M&Eacute;RIDA NORTE", "M&Eacute;RIDA SUR"))
            .put(35, Arrays.asList("CIUDAD DE ZACATECAS", "FRESNILLO"))
            .build();
	
}
