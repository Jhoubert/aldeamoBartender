--
-- Base de datos: `test`
--

create database if not exists bartender;
use bartender;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `arrays`
--

CREATE TABLE `arrays` (
  `id` int(11) NOT NULL,
  `input_array` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `arrays`
--

INSERT INTO `arrays` (`id`, `input_array`) VALUES
(1, '2,4,5,6,7,8'),
(2, '3,7,9,5,4,2'),
(3, '5,7,9,11,13'),
(4, '6,4,2,12,15'),
(5, '7,10,15,11,9');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `arrays`
--
ALTER TABLE `arrays`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `arrays`
--
ALTER TABLE `arrays`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
