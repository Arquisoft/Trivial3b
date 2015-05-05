# language: es
Característica: Mover ficha

Escenario: Esperando turno de juego
    Dado un juego creado
    Cuando creo un jugador de nombre "Usuario1" y pass "Usuario1"
    Cuando creo un jugador de nombre "Usuario2" y pass "Usuario2"
    Entonces el jugador actual es "Usuario1"
	Entonces el jugador actual no es "Usuario2"