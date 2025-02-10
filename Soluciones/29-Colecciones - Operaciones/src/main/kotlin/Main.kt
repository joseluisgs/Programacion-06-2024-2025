package dev.joseluisgs

data class Vehiculo(
    val marca: String,
    val modelo: String,
    val añoFabricacion: Int,
    val color: String,
    val consumo: Double, // litros cada 100 km
    val kilometraje: Int
)


fun main() {
    // Println()
    val vehiculos = listOf(
        Vehiculo("Toyota", "Corolla", 2018, "Rojo", 6.5, 85000),
        Vehiculo("Ford", "Fiesta", 2020, "Azul", 5.8, 40000),
        Vehiculo("Honda", "Civic", 2017, "Negro", 7.0, 120000),
        Vehiculo("Chevrolet", "Onix", 2021, "Blanco", 5.3, 25000),
        Vehiculo("Volkswagen", "Golf", 2019, "Gris", 6.8, 60000),
        Vehiculo("Nissan", "Versa", 2016, "Plateado", 6.2, 130000),
        Vehiculo("Hyundai", "Tucson", 2022, "Rojo", 7.5, 15000),
        Vehiculo("Mazda", "3", 2015, "Azul", 6.0, 140000),
        Vehiculo("BMW", "3 Series", 2020, "Blanco", 6.5, 100000),
        Vehiculo("Mercedes-Benz", "E-Class", 2021, "Negro", 6.5, 80000),
        Vehiculo("Toyota", "Celica", 2018, "Rojo", 6.5, 85000)
    )

    // Encuentra todos los vehículos que tengan un consumo menor a 6 litros cada 100 km.
    println("Vehículos con consumo menor a 6 litros cada 100 km:")
    val vehiculosConConsumoMenor = vehiculos.filter { it.consumo < 6.0 }
    println(vehiculosConConsumoMenor)

    // Filtra los vehículos cuyo kilometraje sea mayor a 100,000 km.
    println("Vehículos con kilometraje mayor a 100,000 km:")
    val vehiculosConKilometrajeMayor = vehiculos.filter { it.kilometraje > 100000 }
    println(vehiculosConKilometrajeMayor)

    // Filtra los vehículos cuyo color sea blanco.
    val vehiculosConColorBlanco = vehiculos.filter { it.color == "Blanco" }
    println("Vehículos con color blanco:")

    // Filtra los vehículos cuyo año de fabricación sea posterior a 2018 y de color blanco.
    val vehiculosConColorBlancoYAnioFabricacionPosteriorA2018 = vehiculos
        .filter { it.color == "Blanco" && it.añoFabricacion > 2018 }
        .sortedBy { it.añoFabricacion }

    println(vehiculosConColorBlancoYAnioFabricacionPosteriorA2018)

    //Obtén solo los vehículos de color rojo.
    println("Vehículos de color rojo:")
    val vehiculosColorRojo = vehiculos.filter { it.color == "Rojo" }

    // Buscar vehículo por marca y modelo: Toyota Celica
    println("Vehículo buscado:")
    val vehiculoBuscado = vehiculos.find { it.marca == "Toyota" && it.modelo == "Celica" }
    println(vehiculoBuscado)

    // Obtén una lista de todas las marcas de los vehículos sin repetir.
    println("Marcas de los vehículos:")
    val marcas = vehiculos.map { it.marca }.distinct()
    println(marcas)

    // Obtén una lista de todas las marcas de los vehículos sin repetir y que tenga más de 5 letras.
    println("Marcas de los vehículos:")
    val marcasMasdeCinco = vehiculos.map { it.marca }.distinct().filter { it.length > 5 }
    println(marcasMasdeCinco)

    //Crea una lista de cadenas con el formato "Marca Modelo - Año" para cada vehículo.
    println("Lista de cadenas con el formato \"Marca Modelo - Año\" para cada vehículo:")
    val vehiculosConFormato = vehiculos.map { "${it.marca} ${it.modelo} - ${it.añoFabricacion}" }
    println(vehiculosConFormato)

    // La marca más larga
    println("Marca más larga:")
    val marcaMasLarga = vehiculos.maxByOrNull { it.marca.length }?.marca
    println(marcaMasLarga)

    // Media de consumo de los Toyota
    println("Media de consumo de los Toyota:")
    val mediaConsumoToyota = vehiculos.filter { it.marca == "Toyota" }.map { it.consumo }.average()
    println(mediaConsumoToyota)

    // Ver si hay algún vehículo con más de 200,000 km
    println("Vehículo con más de 200,000 km:")
    val vehiculoConMasDe200000Km = vehiculos.any { it.kilometraje > 200000 }
    println(vehiculoConMasDe200000Km)

    //Ordena los vehículos por año de fabricación de más nuevo a más antiguo.
    println("Vehículos ordenados por año de fabricación de más nuevo a más antiguo:")
    val vehiculosOrdenadosPorAnioFabricacion = vehiculos.sortedBy { it.añoFabricacion }
    println(vehiculosOrdenadosPorAnioFabricacion)

    //Ordena los vehículos por consumo de mayor a menor.
    println("Vehículos ordenados por consumo de menor a mayor:")
    val vehiculosOrdenadosPorConsumo = vehiculos.sortedByDescending { it.consumo }
    println(vehiculosOrdenadosPorConsumo)

    // Agrupa los vehículos por color.
    println("Vehículos agrupados por color:")
    val vehiculosAgrupadosPorColor = vehiculos.groupBy { it.color }
    println(vehiculosAgrupadosPorColor)

    //Agrupa los vehículos por marca y muestra cuántos hay de cada una.
    println("Vehículos agrupados por marca:")
    val vehiculosAgrupadosPorMarca = vehiculos.groupingBy { it.marca }.eachCount()
    println(vehiculosAgrupadosPorMarca)

    // Agrupa los vehículos por año de fabricación y muestra cuántos hay de cada uno.
    println("Vehículos agrupados por año de fabricación:")
    val vehiculosAgrupadosPorAnioFabricacion = vehiculos.groupingBy { it.añoFabricacion }.eachCount()
    println(vehiculosAgrupadosPorAnioFabricacion)

    // Agrupa los vehiculos por año de fabricación y muestra cuántos hay de cada uno, ordenados por cantidad.
    println("Vehículos agrupados por año de fabricación y ordenados por cantidad:")
    val vehiculosAgrupadosPorAnioFabricacionOrdenado =
        vehiculos.groupingBy { it.añoFabricacion }.eachCount().toList().sortedBy { it.second }
    println(vehiculosAgrupadosPorAnioFabricacionOrdenado)

    // Calcula el promedio de consumo de los vehículos.
    println("Promedio de consumo de los vehículos:")
    val promedioConsumo = vehiculos.map { it.consumo }.average()
    println(promedioConsumo)

    //Encuentra el vehículo con mayor kilometraje.
    println("Vehículo con mayor kilometraje:")
    val vehiculoConMayorKilometraje = vehiculos.maxByOrNull { it.kilometraje }
    println(vehiculoConMayorKilometraje)

    //Calcula la cantidad de vehículos de cada año de fabricación.
    println("Cantidad de vehículos de cada año de fabricación:")
    val cantidadVehiculosPorAnioFabricacion = vehiculos.groupingBy { it.añoFabricacion }.eachCount()
    println(cantidadVehiculosPorAnioFabricacion)

    //Calcula el total de kilómetros recorridos por todos los vehículos.
    println("Total de kilómetros recorridos por todos los vehículos:")
    val totalKilometrosRecorridos = vehiculos.sumOf { it.kilometraje }
    println(totalKilometrosRecorridos)

    // Calcula la cantidad de vehículos que superan el consumo promedio de todos los vehículos.
    println("Cantidad de vehículos que superan el consumo promedio:")
    val cantidadVehiculosConsumoSuperiorPromedio =
        vehiculos.count { it.consumo > vehiculos.map { it.consumo }.average() }
    println(cantidadVehiculosConsumoSuperiorPromedio)

    // Calcula la cantidad de vehículos que superan el kilometraje promedio de todos los vehículos.
    println("Cantidad de vehículos que superan el kilometraje promedio:")
    val promedioKilometraje = vehiculos.map { it.kilometraje }.average()
    val cantidadVehiculosKilometrajeSuperiorPromedio = vehiculos.count { it.kilometraje > promedioKilometraje }
    println(cantidadVehiculosKilometrajeSuperiorPromedio)

    // Agrupa los vehículos por año de fabricación obteniendo el promedio de consumo de cada uno.
    println("Vehículos agrupados por año de fabricación y promedio de consumo:")
    val vehiculosAgrupadosPorAnioFabricacionPromedioConsumo =
        vehiculos.groupBy { it.añoFabricacion }.mapValues { it.value.map { it.consumo }.average() }

    // Encuentra los vehículos cuyo consumo sea menor a 6.5 litros y tengan menos de 100,000 km recorridos.
    println("Vehículos con consumo menor a 6.5 litros y menos de 100,000 km recorridos:")
    val vehiculosConsumoMenorYKilometrajeMenor = vehiculos.filter { it.consumo < 6.5 && it.kilometraje < 100000 }
    println(vehiculosConsumoMenorYKilometrajeMenor)

    //Obtén los vehículos fabricados entre 2015 y 2020 que no sean de color negro ni blanco.
    val vehiculosEntre2015Y2020 =
        vehiculos.filter { it.añoFabricacion in 2015..2020 && it.color != "negro" && it.color != "blanco" }
    println(vehiculosEntre2015Y2020)


    //Filtra los vehículos cuya marca comience con la letra "T" o "H" y que tengan más de 50,000 km.
    val vehiculosMarcaTComienzaConT =
        vehiculos.filter { it.marca.firstOrNull() in listOf('T', 'H') && it.kilometraje > 50000 }
    println(vehiculosMarcaTComienzaConT)

    //Crea un mapa donde las claves sean las marcas de los vehículos y los valores una lista de modelos correspondientes.
    val marcasConModelos = vehiculos.groupBy { it.marca }.mapValues { it.value.map { it.modelo } }
    println(marcasConModelos)

    //Convierte la lista de vehículos en un solo String con el formato: "Marca Modelo (Año) - Consumo L/100km" separados por comas.
    val vehiculosFormatoString =
        vehiculos.map { "${it.marca} ${it.modelo} (${it.añoFabricacion}) - ${it.consumo} L/100km" }.joinToString(", ")
    println(vehiculosFormatoString)

    //Obtén una lista con los vehículos ordenados por consumo, pero en caso de empate, ordenados por kilometraje descendente.
    val vehiculosOrdenadosPorConsumoYKilometraje = vehiculos.sortedWith(compareBy({ it.consumo }, { -it.kilometraje }))
    println(vehiculosOrdenadosPorConsumoYKilometraje)

    //Calcula el consumo promedio de los vehículos fabricados después de 2018.
    val promedioConsumoVehiculosPost2018 = vehiculos.filter { it.añoFabricacion > 2018 }.map { it.consumo }.average()
    println(promedioConsumoVehiculosPost2018)


    //Encuentra el vehículo con el mejor rendimiento (menor consumo).
    val vehiculoMejorRendimiento = vehiculos.minByOrNull { it.consumo }
    println(vehiculoMejorRendimiento)

    //Calcula cuántos vehículos tienen más de 100,000 km recorridos.
    val cantidadVehiculosMasDe100000Km = vehiculos.count { it.kilometraje > 100000 }
    println(cantidadVehiculosMasDe100000Km)

    //Determina si todos los vehículos tienen un consumo menor a 8 litros.
    val todosVehiculosConsumoMenor8Litros = vehiculos.all { it.consumo < 8 }
    println(todosVehiculosConsumoMenor8Litros)


    //Verifica si hay al menos un vehículo rojo con menos de 50,000 km.
    val hayVehiculoRojoMenosDe50000Km = vehiculos.any { it.color == "rojo" && it.kilometraje < 50000 }
    println(hayVehiculoRojoMenosDe50000Km)

    //Encuentra el color que aparece con más frecuencia en la lista.
    val colorMasFrecuente = vehiculos.groupBy { it.color }.maxByOrNull { it.value.size }?.key
    println(colorMasFrecuente)

    // Encuetra el valor del color que aparece con más frecuencia en la lista y cuántos vehículos lo tienen.
    val cantidadColorMasFrecuente = vehiculos.groupBy { it.color }.maxByOrNull { it.value.size }?.value?.size
    println(cantidadColorMasFrecuente)

    //Encuentra la marca con el mayor número de vehículos en la lista y muestra cuántos hay.
    val marcaConMasVehiculos = vehiculos.groupingBy { it.marca }.eachCount().maxByOrNull { it.value }?.key
    val cantidadMarcaConMasVehiculos = vehiculos.groupingBy { it.marca }.eachCount().maxByOrNull { it.value }?.value
    println("Marca con más vehículos: $marcaConMasVehiculos, Cantidad: $cantidadMarcaConMasVehiculos")

    //Dime todos los años de fabricación de manera ascendente
    val añosFabricacionUnicos = vehiculos.map { it.añoFabricacion }.distinct().sorted()
    println(añosFabricacionUnicos)

    //Agrupa vehiculos por marca y luego obten aquellas cuales tienen más de 2 vehículos
    val vehiculosPorMarca = vehiculos.groupBy { it.marca }.filterValues { it.size > 2 }
    println(vehiculosPorMarca)

}