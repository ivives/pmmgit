-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-04-2014 a las 22:39:51
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
(3600541238961, 'Mascarilla Fructis Garnier', 4.5),
(3700123300236, 'Agua Aquarel 6x33cl', 1.5),
(4015600715755, 'Ariel Actilift 30 lavados', 6.99),
(5029053038001, 'Scottex 24 uds.', 6.95),
(5449000000996, 'Lata CocaCola', 0.56),
(5449000006004, 'Lata Fanta Limon', 0.43),
(5449000011527, 'Lata Fanta Naranja', 0.43),
(5449000131805, 'Lata CocaCola cero', 0.56),
(5906747316013, 'Osito Lulu Fontaneda', 1.7),
(7622210055828, 'Queso porciones El Caserio 24 uds.', 2.3),
(8410014232335, 'ColaCao 3 kg', 14.95),
(8410014308610, 'Nocilla 400 g', 2.5),
(8410055150018, 'Agua Font Vella 1.5 litros', 0.53),
(8410069006585, 'Gallo Helices con vegetales', 1.05),
(8410069010803, 'Gallo Corazones con vegetales', 1.05),
(8410084041110, 'Arroz La Fallera', 1.85),
(8410090104311, 'Atun claro calvo 6 latas', 4.45),
(8410100065144, 'Pure de patatas Maggi', 2.2),
(8410100180045, 'Papilla 8 cereales 1200 gr.', 6.2),
(8410128276003, 'Bifrutas Pascual 6 uds', 2.56),
(8410180225087, 'Galletas Dino', 2.09),
(8410300349006, 'Caldo de cocido Gallina blanca', 1.95),
(8410300349044, 'Caldo de carne Gallina blanca', 1.95),
(8410300349051, 'Caldo de pollo Gallina blanca', 1.95),
(8410500006266, 'Vitalinea Danone natrural edulcorado 4 uds.', 1),
(8410500006273, 'Vitalinea Danone limon 4 uds.', 1),
(8410714102129, 'Azucar 1 Kg', 0.93),
(8411384005062, '12 huevos frescos tamano L', 1.49),
(8411831500164, 'Vinagre de Modena 250 ml', 1.59),
(8412500927992, 'Pate La Piara 3 latas', 2.85),
(8480000180025, 'Atun en aceite de oliva 6 latas', 3.95),
(8480000260390, 'Garbanzos bote 300 gr.', 0.43),
(8480000450814, 'Jabon de manos', 0.75),
(8480000478160, 'Toallitas humedas Wc', 1.95),
(8480000497505, 'Bolsas basura cierra facil 40 uds.', 1.6),
(8480000771155, 'Bastoncillos de algodon', 0.55),
(8712000010294, 'Heineken p-8 latas', 4.4),
(8722700090366, 'Crema de verduras mediterraneas Knorr', 1.45),
(8722700090571, 'Crema calabaza Knorr', 1.45),
(8722700179337, 'Crema de zanahoria Knorr', 1.45),
(8722700781523, 'Crema setas del bosque Knorr', 1.45),
(8722700781646, 'Crema calabacin Knorr', 1.45);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
