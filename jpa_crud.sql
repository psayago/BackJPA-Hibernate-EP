-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-07-2020 a las 07:01:43
-- Versión del servidor: 10.1.39-MariaDB
-- Versión de PHP: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `jpa_crud`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `ID_CLIENTE` int(11) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `APELLIDO` varchar(100) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `ESVIP` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`ID_CLIENTE`, `NOMBRE`, `APELLIDO`, `EMAIL`, `ESVIP`) VALUES
(6, 'Esteban', 'Quito', 'estebanquito@gmail.com', 0),
(7, 'Rocio', 'Martinez', 'romartinez@gmail.com', 0),
(8, 'Claudia', 'Roman', 'croman@gmail.com', 0),
(12, 'Claudia', 'Roman', 'croman@gmail.com', 0),
(13, 'Pedro', 'Picapiedra', 'cromañon@altavista.com', 0),
(14, 'Ibis', 'Fortunato', 'ibis@gmail.com', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE `facturas` (
  `NRO_FACTURA` int(11) NOT NULL,
  `ID_CLIENTE` int(11) NOT NULL,
  `TOTAL` double NOT NULL,
  `FECHA` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `facturas`
--

INSERT INTO `facturas` (`NRO_FACTURA`, `ID_CLIENTE`, `TOTAL`, `FECHA`) VALUES
(1, 7, 0, NULL),
(2, 8, 0, NULL),
(3, 14, 235999.94, NULL),
(4, 13, 136999.96, '2020-07-30 03:31:23'),
(5, 13, 88999.98, '2020-07-30 03:53:17'),
(6, 6, 59999.99, '2020-07-30 04:23:15'),
(7, 13, 59999.99, '2020-07-30 04:30:52'),
(8, 8, 18999.99, '2020-07-30 04:41:00'),
(9, 8, 78999.98, '2020-07-30 04:46:51'),
(10, 14, 119999.98, '2020-07-30 04:51:04');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(9),
(9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `ID_PRODUCTO` int(11) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `DESCRIPCION` varchar(100) NOT NULL,
  `PRECIO` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`ID_PRODUCTO`, `NOMBRE`, `DESCRIPCION`, `PRECIO`) VALUES
(3, 'Laptop HP LPT1512', 'Laptop con procesador i7, 16mb de RAM y HDD de 1TB', 28999.99),
(4, 'Laptop lenovo vostro 1512', 'Laptop con procesador i5, 16mb de RAM y HDD de 240gb', 28999.99),
(5, 'Monitor Sentey 24\'', 'Monitor gamer 144hz', 18999.99),
(6, 'Playstation 4', 'Playstation 4 con dos joystick y 1 juego', 59999.99),
(7, 'Xbox ONE', 'Xbox ONE con dos joystick y 1 juego', 59999.99);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `ID_VENTA` int(11) NOT NULL,
  `ID_PRODUCTO` int(11) NOT NULL,
  `CANTIDAD` int(11) NOT NULL,
  `NRO_FACTURA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`ID_VENTA`, `ID_PRODUCTO`, `CANTIDAD`, `NRO_FACTURA`) VALUES
(1, 3, 2, 2),
(2, 3, 3, 3),
(3, 4, 1, 3),
(4, 6, 2, 3),
(5, 3, 1, 4),
(6, 4, 1, 4),
(7, 5, 1, 4),
(8, 6, 1, 4),
(9, 6, 1, 5),
(10, 3, 1, 5),
(11, 6, 1, 6),
(12, 6, 1, 7),
(13, 5, 1, 8),
(14, 5, 1, 9),
(15, 6, 1, 9),
(16, 7, 1, 10),
(17, 6, 1, 10);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`ID_CLIENTE`);

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`NRO_FACTURA`),
  ADD KEY `ID_CLIENTE` (`ID_CLIENTE`) USING BTREE;

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`ID_PRODUCTO`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`ID_VENTA`),
  ADD KEY `NRO_FACTURA` (`NRO_FACTURA`),
  ADD KEY `ID_PRODUCTO` (`ID_PRODUCTO`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `ID_CLIENTE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `facturas`
--
ALTER TABLE `facturas`
  MODIFY `NRO_FACTURA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `ID_PRODUCTO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `ID_VENTA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `FK25hwggt4ueardlf4m802dbl1s` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`ID_CLIENTE`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `FKe67el1uag6ehvvh36qujv7r5k` FOREIGN KEY (`ID_PRODUCTO`) REFERENCES `productos` (`ID_PRODUCTO`),
  ADD CONSTRAINT `FKkw8gbcw0hqnfgl18rq1hnukev` FOREIGN KEY (`NRO_FACTURA`) REFERENCES `facturas` (`NRO_FACTURA`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
