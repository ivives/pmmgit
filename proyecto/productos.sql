-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-05-2014 a las 19:22:13
-- Versión del servidor: 5.5.34
-- Versión de PHP: 5.4.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `supermercado`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE IF NOT EXISTS `productos` (
  `codigo` bigint(13) NOT NULL,
  `descripcion` varchar(50) CHARACTER SET utf32 COLLATE utf32_bin NOT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`codigo`, `descripcion`, `precio`) VALUES
(3600541238961, 'MASCARILLA FRUCTIS GARNIER', 4.5),
(3700123300236, 'AGUA AQUAREL 6x33 CL', 1.5),
(4015600715755, 'ARIEL ACTILIFT 30 LAV', 6.99),
(5029053038001, 'SCOTTEX 24 UDS', 6.95),
(5449000000996, 'COCACOLA LATA', 0.56),
(5449000006004, 'FANTA LIMON LATA', 0.43),
(5449000011527, 'FANTA NARANJA LATA', 0.43),
(5449000131805, 'COCACOLA ZERO LATA', 0.56),
(5906747316013, 'OSITO LULU FONTANEDA', 1.7),
(7622210055828, 'QUESO PORCIONES 24 UDS', 2.3),
(8410014232335, 'COLACAO 3 KG', 14.95),
(8410014308610, 'NOCILLA 400 GR', 2.5),
(8410055150018, 'AGUA FONT VELLA 1.5 L', 0.53),
(8410069006585, 'GALLO HELICES CON VEGETALES', 1.05),
(8410069010803, 'GALLO LAZOS CON VEGETALES', 1.05),
(8410084041110, 'ARROZ LA FALLERA', 1.85),
(8410090104311, 'ATUN CLARO CALVO 6 UDS', 4.45),
(8410100065144, 'PURE PATATAS MAGGI', 2.2),
(8410100180045, 'PAPILLA 8 CEREALES 1200 GR', 6.2),
(8410128276003, 'BIFRUTAS PASCUAL 6 UDS', 2.56),
(8410180225087, 'GALLETAS DINO', 2.09),
(8410300349006, 'CALDO COCIDO GALLINA BLANCA', 1.95),
(8410300349044, 'CALDO CARNE GALLINA BLANCA', 1.95),
(8410300349051, 'CALDO POLLO GALLINA BLANCA', 1.95),
(8410500006266, 'VITALINEA NATURAL EDUL 4 UDS', 1),
(8410500006273, 'VITALINEA LIMON 4 UDS', 1),
(8410714102129, 'AZUCAR 1 KG', 0.93),
(8411384005062, 'HUEVOS FRESCOS 12 UDS', 1.49),
(8411700005837, 'BATIDO CACAO PULEVA 6 UDS', 1.72),
(8411831500164, 'VINAGRE MODENA 250 ML', 1.59),
(8412500927992, 'PATE LA PIARA 3 LATAS', 2.85),
(8414100314257, 'SUNNY FLORIDA 4 UDS', 1.34),
(8480000180025, 'ATUN ACEITE OLIVA 6 LATAS', 3.95),
(8480000260390, 'GARBANZOS BOTE 300 GR', 0.43),
(8480000450814, 'JABON DE MANOS', 0.75),
(8480000478160, 'TOALLITAS HUMEDAS WC', 1.95),
(8480000497505, 'BOLSAS BASURA 40 UDS', 1.6),
(8480000771155, 'BASTONCILLOS ALGODON', 0.55),
(8712000010294, 'HEINEKEN 8 LATAS', 4.4),
(8722700090366, 'CREMA VERDURAS KNORR', 1.45),
(8722700090571, 'CREMA CALABAZA KNORR', 1.45),
(8722700179337, 'CREMA ZANAHORIA KNORR', 1.45),
(8722700781523, 'CREMA SETAS DEL BOSQUE KNORR', 1.45),
(8722700781646, 'CREMA CALABACIN KNORR', 1.45);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
