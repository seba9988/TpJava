-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-06-2022 a las 03:46:17
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ligafut`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `arbitro`
--

CREATE TABLE `arbitro` (
  `dniArbitro` varchar(10) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `fechaNac` date DEFAULT NULL,
  `fecha_baja` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `arbitro`
--

INSERT INTO `arbitro` (`dniArbitro`, `nombre`, `apellido`, `fechaNac`, `fecha_baja`) VALUES
('1111111', 'Pierluigi', 'Collina', '1960-02-13', NULL),
('2222222', 'Horacio', 'Elizondo', '1963-11-01', NULL),
('3333333', 'Gianluca', 'Rocchi', '1973-08-25', NULL),
('4444444', 'Hector', 'Baldassi', '1966-01-05', NULL),
('45415454', 'aaa', '222sdd', '2022-05-17', '2022-05-19'),
('5656565', 'sss', 'aaaa', '2022-05-20', '2022-05-19');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cancha`
--

CREATE TABLE `cancha` (
  `numCancha` int(11) NOT NULL,
  `nombre` varchar(25) DEFAULT NULL,
  `fecha_baja` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cancha`
--

INSERT INTO `cancha` (`numCancha`, `nombre`, `fecha_baja`) VALUES
(1, 'La Bombonera', NULL),
(2, 'King Power Stadium', NULL),
(3, 'Camp Nou', NULL),
(4, 'White Hart Lane', NULL),
(5, 'Parc des Princes', NULL),
(6, 'Signal Iduna Park', NULL),
(10, 'asssddskjjjmn', '2022-05-17'),
(11, 'la cancha', '2022-05-17');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrenador`
--

CREATE TABLE `entrenador` (
  `dniEntrenador` varchar(10) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `fechaNac` date DEFAULT NULL,
  `idEquipo` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `entrenador`
--

INSERT INTO `entrenador` (`dniEntrenador`, `nombre`, `apellido`, `fechaNac`, `idEquipo`) VALUES
('1010101', 'Carlo', 'Ancelotti', '1952-06-10', 3),
('1212121', 'Vincenzo', 'Italiano', '1977-12-10', 1),
('12131415', 'Walter', 'Mazzarri', '1961-10-01', 2),
('1624897', 'Carlos', 'Bianchi', '1949-04-26', 4),
('1689742', 'Claudio', 'Ranieri', '1951-10-20', 5),
('777777', 'Brendan', 'Rodgers', '1973-01-26', NULL),
('888888', 'Antonio', 'Conte', '1969-07-31', NULL),
('99887766', 'Nuno', 'Espirito Santo', '1974-01-25', NULL),
('999999', 'Massimiliano', 'Allegri', '1967-08-11', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `id` int(10) UNSIGNED NOT NULL,
  `razonSocial` varchar(45) DEFAULT NULL,
  `localidad` varchar(45) DEFAULT NULL,
  `escudo` blob DEFAULT NULL,
  `puntaje` int(11) DEFAULT NULL,
  `difGoles` varchar(45) DEFAULT NULL,
  `fecha_baja` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`id`, `razonSocial`, `localidad`, `escudo`, `puntaje`, `difGoles`, `fecha_baja`) VALUES
(1, 'Leicester City', 'Leicester', NULL, 3, '1', NULL),
(2, 'Tottenham Hotspur', 'Londres', NULL, 3, '2', NULL),
(3, 'Real Madrid', 'Madrid', NULL, 1, '0', NULL),
(4, 'Juventus', 'Turin', NULL, 1, '0', NULL),
(5, 'Florentina', 'Florencia', NULL, 0, '-2', NULL),
(6, 'Cagliari Calcio', 'Cagliari', NULL, 0, '-1', NULL),
(7, 'La masturbanda', 'casa1111', NULL, 0, '0', '2022-05-19'),
(12, 'sdf', 'fds', NULL, 4, '4', '2022-05-19');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jugador`
--

CREATE TABLE `jugador` (
  `dniJugador` varchar(10) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `fechaNac` date DEFAULT NULL,
  `posicion` varchar(15) DEFAULT NULL,
  `goles` int(11) DEFAULT NULL,
  `asistencias` int(11) DEFAULT NULL,
  `amarillas` int(11) DEFAULT NULL,
  `rojas` int(11) DEFAULT NULL,
  `partJugados` int(11) DEFAULT NULL,
  `idEquipo` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `jugador`
--

INSERT INTO `jugador` (`dniJugador`, `nombre`, `apellido`, `fechaNac`, `posicion`, `goles`, `asistencias`, `amarillas`, `rojas`, `partJugados`, `idEquipo`) VALUES
('11598789', 'Kasper', 'Schmeichel', '1986-11-05', 'arquero', 1, 2, 3, 0, 12, 1),
('12361296', 'Jamie', 'Vardy', '1987-01-11', 'delantero', 15, 8, 3, 0, 10, 5),
('15161665', 'Hugo', 'Lloris', '1986-12-26', 'arquero', 0, 1, 4, 1, 15, 1),
('15648753', 'Kevin', 'Strootman', '1990-02-13', 'mediocampista', 2, 5, 2, 0, 10, NULL),
('15662196', 'Karim', 'Benzema', '1987-12-19', 'delantero', 12, 5, 1, 0, 20, 3),
('18112110', 'Marco', 'Benasi', '1994-09-08', 'mediocampista', 8, 9, 2, 1, 22, NULL),
('18965221', 'Lorenzo', 'Venuti', '1995-04-12', 'defensor', 2, 1, 1, 0, 20, 5),
('20154196', 'Harry', 'Kane', '1993-07-28', 'delantero', 12, 8, 4, 0, 18, 4),
('21630123', 'Lucas', 'Torreira', '1996-02-11', 'mediocampista', 5, 2, 3, 1, 18, NULL),
('22321496', 'Luka', 'Modric', '1985-09-09', 'mediocampista', 4, 10, 2, 0, 15, 3),
('25161296', 'Heung-min', 'Son', '1992-08-07', 'delantero', 10, 7, 3, 1, 15, 5),
('25661665', 'Thibaut', 'Courtois', '1991-05-11', 'arquero', 0, 0, 3, 0, 16, 3),
('26161628', 'Gareth', 'Bale', '1989-07-16', 'delantero', 8, 9, 2, 1, 22, 3),
('30961210', 'Dani', 'Carvajal', '1992-01-11', 'defensor', 5, 2, 3, 1, 18, 3),
('31961215', 'Davinson', 'Sanchez', '1996-06-12', 'defensor', 1, 0, 8, 3, 15, 2),
('34961296', 'Youri', 'Tielemans', '1997-05-07', 'mediocampista', 5, 10, 3, 0, 15, NULL),
('35961296', 'Wesley', 'Fofana', '2000-12-17', 'defensor', 2, 3, 6, 1, 8, 2),
('36546921', 'Alberto', 'Grassi', '1995-03-07', 'mediocampista', 5, 6, 4, 1, 11, 4),
('38465892', 'Razvan', 'Marin', '1996-05-23', 'delantero', 10, 4, 3, 0, 10, 5),
('38961215', 'James', 'Maddison', '1996-11-23', 'mediocampista', 3, 9, 4, 1, 12, 4),
('39825647', 'Alessio', 'Cragno', '1994-06-28', 'arquero', 0, 2, 2, 0, 11, 1),
('42569874', 'Giorgio', 'Altare', '1998-08-09', 'defensor', 2, 1, 5, 2, 9, 2),
('43405894', 'Dusan', 'Vlahovic', '2000-01-28', 'delantero', 12, 5, 4, 0, 12, 5),
('46156812', 'Pietro', 'Terracciano', '1990-03-08', 'arquero', 0, 0, 3, 0, 16, 1),
('46521012', 'Giorgio', 'Chiellini', '1984-08-14', 'defensor', 1, 0, 3, 0, 16, 2),
('48749221', 'Álvaro', 'Morata', '1992-10-23', 'delantero', 9, 6, 2, 0, 15, 4),
('61212110', 'Wojciech', 'Szczesny', '1990-04-18', 'arquero', 0, 2, 2, 1, 22, 1),
('68984221', 'Leonardo', 'Bonucci', '1987-05-01', 'defensor', 3, 2, 1, 0, 20, 2),
('95130123', 'Rodrigo', 'Bentancur', '1997-06-25', 'mediocampista', 5, 2, 3, 1, 18, NULL),
('97549221', 'Jose', 'Callejon', '1987-02-11', 'delantero', 7, 4, 2, 0, 15, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partido`
--

CREATE TABLE `partido` (
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `resultado` varchar(15) DEFAULT NULL,
  `incidencias` varchar(45) DEFAULT NULL,
  `idEquipo1` int(10) UNSIGNED DEFAULT NULL,
  `idEquipo2` int(10) UNSIGNED DEFAULT NULL,
  `numCancha` int(11) NOT NULL,
  `dniArbitro` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `partido`
--

INSERT INTO `partido` (`fecha`, `hora`, `resultado`, `incidencias`, `idEquipo1`, `idEquipo2`, `numCancha`, `dniArbitro`) VALUES
('2022-02-26', '19:30:00', '1-2', NULL, 6, 1, 2, '1111111'),
('2022-02-26', '21:15:00', '0-2', NULL, 5, 2, 3, '3333333'),
('2022-02-27', '16:00:00', '1-1', NULL, 4, 3, 5, '2222222'),
('2022-03-05', '17:30:00', 'Reprogramado', 'null', 2, 1, 1, '1111111'),
('2022-03-05', '20:45:00', ' ', NULL, 6, 3, 4, NULL),
('2022-03-06', '18:30:00', ' ', NULL, 5, 4, 6, NULL),
('2022-03-12', '18:30:00', ' ', NULL, 3, 1, 5, NULL),
('2022-03-13', '19:30:00', ' ', NULL, 6, 5, 1, NULL),
('2022-03-13', '21:15:00', ' ', NULL, 2, 4, 3, NULL),
('2022-03-19', '16:15:00', ' ', NULL, 4, 1, 2, NULL),
('2022-03-20', '21:10:00', ' ', NULL, 2, 6, 5, NULL),
('2022-03-26', '18:15:00', ' ', NULL, 4, 1, 3, NULL),
('2022-03-26', '21:30:00', ' ', NULL, 4, 6, 4, NULL),
('2022-05-04', '20:00:00', ' ', NULL, 3, 2, 1, NULL),
('2022-05-15', '19:15:00', '', NULL, 1, 6, 10, '4444444'),
('2022-05-31', '18:45:00', ' ', NULL, 3, 5, 6, '4444444'),
('2022-06-10', '13:00:00', NULL, NULL, 2, 1, 2, '3333333'),
('2022-06-24', '22:01:00', 'Reprogramado', 'null', 5, 4, 1, '2222222'),
('2022-06-25', '00:05:00', NULL, NULL, 5, 4, 4, '2222222');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `arbitro`
--
ALTER TABLE `arbitro`
  ADD PRIMARY KEY (`dniArbitro`);

--
-- Indices de la tabla `cancha`
--
ALTER TABLE `cancha`
  ADD PRIMARY KEY (`numCancha`);

--
-- Indices de la tabla `entrenador`
--
ALTER TABLE `entrenador`
  ADD PRIMARY KEY (`dniEntrenador`),
  ADD KEY `fkEntrenadorEquipo` (`idEquipo`);

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD PRIMARY KEY (`dniJugador`),
  ADD KEY `fkJugadorEquipo` (`idEquipo`);

--
-- Indices de la tabla `partido`
--
ALTER TABLE `partido`
  ADD PRIMARY KEY (`fecha`,`hora`,`numCancha`) USING BTREE,
  ADD KEY `fkPartidoCancha` (`numCancha`),
  ADD KEY `fkPartidoArbitro` (`dniArbitro`),
  ADD KEY `fkPartidoEquipo1` (`idEquipo1`),
  ADD KEY `fkPartidoEquipo2` (`idEquipo2`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cancha`
--
ALTER TABLE `cancha`
  MODIFY `numCancha` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `entrenador`
--
ALTER TABLE `entrenador`
  ADD CONSTRAINT `fkEntrenadorEquipo` FOREIGN KEY (`idEquipo`) REFERENCES `equipo` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD CONSTRAINT `fkJugadorEquipo` FOREIGN KEY (`idEquipo`) REFERENCES `equipo` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `partido`
--
ALTER TABLE `partido`
  ADD CONSTRAINT `fkPartidoArbitro` FOREIGN KEY (`dniArbitro`) REFERENCES `arbitro` (`dniArbitro`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fkPartidoCancha` FOREIGN KEY (`numCancha`) REFERENCES `cancha` (`numCancha`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fkPartidoEquipo1` FOREIGN KEY (`idEquipo1`) REFERENCES `equipo` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fkPartidoEquipo2` FOREIGN KEY (`idEquipo2`) REFERENCES `equipo` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
