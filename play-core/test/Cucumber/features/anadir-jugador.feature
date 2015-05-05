# language: es
Característica: Añadir jugadores

Escenario: Crear el primer usuario
    Dado un juego creado
    Cuando creo un jugador de nombre "Usuario1" y pass "Usuario1"
    Entonces el jugador actual es "Usuario1"