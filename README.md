# MinimunEquivalentAutomata

Este código permite ingresar un automata de Mealy o de Moore y nos genera su automata conexo y su mínimo equivalente.

Como se usa:
Primero se elige si será un automata de Moore o de Mealy. Luego se ingresan los estados separados por “;” (ej: “q1;q2;q3;q4”) en el caso de uno de Mealy. Si es un automata de Moore se escriben el estado y su respectivo value (ej: q0,0;q1,1;q2,0;q3,0 ). Finalmente se escriben las relaciones que tiene cada estado , donde el primer numero hace referencia al output necesitado para ir al desde el estado fuente al destino (output,fuente,destino) , en el caso de Mealy se agrega al comienzo el input para tener una estructura como esta (input,output,fuente,destino), y cada relación se separa por “;”.
(ej:0,q0,q1;1,q0,q2;0,q1,q1;1,q1,q2;0,q2,q1;1,q2,q2;0,q3,q0;1,q3,q2)  para Moore
 y un ejemplo de Mealy (0,0,A,B;1,0,A,C;0,0,B,A;1,0,B,D;0,0,C,E;1,0,C,C;0,0,D,E;1,0,D,C;0,0,E,A;1,0,E,G;0,0,F,D;1,0,F,A;0,0,G,E;1,1,G,H;0,1,H,H;1,1,H,H)

Apartir de esto podemos generar el automata conexo con el método obtainECA que nos solicita el automata previamente creado correctamente y luego con el método partitioning full que también solicita el automata previamente creado correctamente obtener su mínimo equivalente
