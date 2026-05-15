section .note.GNU-stack noalloc noexec nowrite progbits
section .data               
;Cambiar Nombre y Apellido por vuestros datos.
developer db "_Ivan_ _Miranda_",0

;Constante que también está definida en C.
DIMMATRIX    equ 4      
SIZEMATRIX   equ DIMMATRIX*DIMMATRIX ;=16

section .text            

;Variables definidas en ensamblador.
global developer                        

;Subrutinas de ensamblador que se llaman desde C.
global showNumberP2, updateBoardP2, copyMatrixP2,
global rotateMatrixLRP2, shiftNumbersLP2, addPairsLP2
global checkEndP2, playP2

;Variables definides en C.
extern m, mUndo, state

;Funciones de C que se llaman desde ensamblador.
extern clearScreen_C,  gotoxyP2_C, getchP2_C, printchP2_C
extern printBoardP2_C, insertTileP2_C, printMessageP2_C   

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ATENCIÓN: Recordad que en ensamblador las variables y los parámetros 
;;   de tipo 'char' se deben asignar a registros de tipo  
;;   BYTE (1 byte): al, ah, bl, bh, cl, ch, dl, dh, sil, dil, ..., r15b
;;   los de tipo 'short' se deben asignar a registros de tipo 
;;   WORD (2 bytes): ax, bx, cx, dx, si, di, ...., r15w
;;   los de tipo 'int' se deben asignar a registros de tipo 
;;   DWORD (4 bytes): eax, ebx, ecx, edx, esi, edi, ...., r15d
;;   los de tipo 'long' se deben asignar a registros de tipo 
;;   QWORD (8 bytes): rax, rbx, rcx, rdx, rsi, rdi, ...., r15
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Las subrutinas en ensamblador que se debe modificar para
;; adaptar su funcionalidad e implementar el paso de parámetros son:
;;   showNumberP2, updateBoardP2, copyMatrixP2, 
;;   rotateMatrixLRP2, shiftNumbersLP2, addPairsLP3
;; La subrutina que hay que implementar es:
;;   checkEndP2
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Esta subrutina se da hecha. NO LA PODEIS MODIFICAR.
; Situar el cursor en una fila y una columna de la pantalla
; en función de la fila (edi) y de la columna (esi) recibidos como 
; parámetro llamando a la función gotoxyP2_C.
; 
; Variables globales utilizadas:	
; Ninguna
; 
; Parámetros de entrada: 
; (rowScreen): rdi(edi) : Fila de la pantalla donde se sitúa el cursor.
; (colScreen): rsi(esi) : Columna de la pantalla donde se sitúa el cursor.
;
; Parámetros de salida: 
; Ninguno
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
gotoxyP2:
   push rbp
   mov  rbp, rsp
   ;guardamos el estado de los registros del procesador porque
   ;las funciones de C no mantienen el estado de los registros.
   push rax
   push rbx
   push rcx
   push rdx
   push rsi
   push rdi
   push r8
   push r9
   push r10
   push r11
   push r12
   push r13
   push r14
   push r15

   ; Cuando llamamos a la función gotoxyP2_C(int row_num, int col_num) desde ensamblador 
   ; el primer parámetro (row_num) debe pasarse por el registro rdi(edi), y
   ; el segundo  parámetro (col_num) debe pasarse por el registro rsi(esi).	
   call gotoxyP2_C
 
   ;restaurar el estado de los registros que se han guardado en la pila.
   pop r15
   pop r14
   pop r13
   pop r12
   pop r11
   pop r10
   pop r9
   pop r8
   pop rdi
   pop rsi
   pop rdx
   pop rcx
   pop rbx
   pop rax

   mov rsp, rbp
   pop rbp
   ret


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Esta subrutina se da hecha. NO LA PODEIS MODIFICAR.
; Mostrar un carácter (dil) en la pantalla, recibido como parámetro, 
; en la posición donde está el cursor llamando a la función printchP2_C.
; 
; Variables globales utilizadas:	
; Ninguna
; 
; Parámetros de entrada: 
; (c) : rdi(dil) : Carácter a mostrar.
; 
; Parámetros de salida: 
; Ninguno
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
printchP2:
   push rbp
   mov  rbp, rsp
   ;guardamos el estado de los registros del procesador porque
   ;las funciones de C no mantienen el estado de los registros.
   push rax
   push rbx
   push rcx
   push rdx
   push rsi
   push rdi
   push r8
   push r9
   push r10
   push r11
   push r12
   push r13
   push r14
   push r15

   ; Cuando llamamos a la función printchP2_C(char c) desde ensamblador, 
   ; el parámetro (c) debe pasarse por el registro rdi(dil).
   call printchP2_C
 
   ;restaurar el estado de los registros que se han guardado en la pila.
   pop r15
   pop r14
   pop r13
   pop r12
   pop r11
   pop r10
   pop r9
   pop r8
   pop rdi
   pop rsi
   pop rdx
   pop rcx
   pop rbx
   pop rax

   mov rsp, rbp
   pop rbp
   ret
   

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Esta subrutina se da hecha. NO LA PODEIS MODIFICAR.
; Leer una tecla y retornar el carácter asociado (al) sin 
; mostrarlo por pantalla, llamando a la función getchP2_C.
; 
; Variables globales utilizadas:	
; Ninguna
; 
; Parámetros de entrada: 
; Ninguno
; 
; Parámetros de salida: 
; (c) : rax(al) : Carácter leído desde el teclado.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
getchP2:
   push rbp
   mov  rbp, rsp
   ;guardamos el estado de los registros del procesador porque
   ;las funciones de C no mantienen el estado de los registros.
   push rbx
   push rcx
   push rdx
   push rsi
   push rdi
   push r8
   push r9
   push r10
   push r11
   push r12
   push r13
   push r14
   push r15
   
   mov rax, 0
   ; Cuando llamamos a la función getchP2_C desde ensamblador, 
   ; retorna sobre el registro rax(al) el carácter leído
   call getchP2_C
 
   ;restaurar el estado de los registros que se han guardado en la pila.
   pop r15
   pop r14
   pop r13
   pop r12
   pop r11
   pop r10
   pop r9
   pop r8
   pop rdi
   pop rsi
   pop rdx
   pop rcx
   pop rbx
   
   mov rsp, rbp
   pop rbp
   ret 


;;;;;
; Convierte el número de la variable (num) de tipo long de 6 dígitos
; (0 <= num <= 999999), recibida como parámetro,
; a caracteres ASCII que representan su valor.
; Si (num) es menor que 0 cambiamos el valor a 0,
; si es mayor que 999999, cambiaremos el valor a 999999.
; Se debe dividir(/) el valor entre 10, de forma iterativa,
; hasta obtener el 6 dígitos.
; En cada iteración, el residuo de la división (%) que es un valor
; entre (0-9) indica el valor del dígito a convertir
; a ASCII ('0' - '9') sumando '0' (48 decimal) para poder mostrarlo.
; Cuando el cociente sea 0 mostraremos espacios en la parte no significativa.
; Por ejemplo, si (num)=103 mostraremos "   103" y no "000103".
; Se deben mostrar los dígitos (carácter ASCII) desde la posición
; indicada por las variables (rowScreen) y (colScreen), recibidas como
; parámetro, posición de las unidades, hacia la izquierda.
; El primer dígito que obtenemos son las unidades, después las decenas,
; ..., para mostrar el valor se debe desplazar el cursor una posición
; a la izquierda en cada iteración.
; Para posicionar el cursor se llama a la subrutina gotoxyP2 y para
; mostrar los caracteres en la subrutina printchP2.
;
; Variables globales utilizadas:
; Ninguna
;
; Parámetros de entrada:
; (rowScreen) :rdi(edi): Fila de la pantalla donde posicionamos el cursor.
; (colScreen) :rsi(esi): Columna de la pantalla donde posicionamos el cursor.
; (num)       :rdx(rdx): Número que queremos mostrar.
;
; Parámetros de salida:
; Ninguno
;;;;;
showNumberP2:
   push rbp
   mov  rbp, rsp
   ;guardamos el estado de los registros del procesador porque
   ;las funciones de C no mantienen el estado de los registros.
	push rdi ;rowScreen
	push rsi ;colScreen
	push rdx ;num

	mov rax, rdx;
	
  
  
  
   sn_end:
   ;restaurar el estado de los registros que se han guardado en la pila.
   pop rdx
   pop rsi
   pop rdi

   mov rsp, rbp
   pop rbp
   ret


;;;;;
; Actualizar el contenido del Tablero de Juego con los datos de la
; matriz (m) de 4x4 de tipo int y los puntos del marcador
; (score), recibido como parámetro, que se han realizado.
; Se debe recorrer toda la matriz (m), y para cada elemento de la matriz
; posicionar el cursor en la pantalla y mostrar el número
; de esa posición de la matriz.
; Recorrer toda la matriz por filas de izquierda a derecha y de arriba a abajo.
; Para recorrer la matriz en ensamblador el índice va de 0 (posición [0][0])
; a 60 (posición [3][3]) con incrementos de 4 porque los datos son de
; tipo int(DWORD) 4 bytes.
; Luego, mostrar el marcador (score) en la parte inferior del tablero,
; fila 18, columna 35 llamando la subrutina showNumberP2.
; Por último posicionar el cursor en la fila 18, columna 36 llamando la
; subrutina gotoxyP2.
;
; Variables globales utilizadas:
; Ninguna
;
; Parámetros de entrada:
; (m)     :rdi(rdi): Dirección de la matriz dond guardamos los números del juego.
; (score) :rsi(rsi): Puntos acumulados en el marcador.
;
; Parámetros de salida:
; Ninguno
;;;;;  
updateBoardP2:
   push rbp
   mov  rbp, rsp
   ;guardamos el estado de los registros del procesador porque
   ;las funciones de C no mantienen el estado de los registros.
   push rdi ;m 
   push rsi ;score
   
   
   
   ub_end:
   ;restaurar el estado de los registros que se han guardado en la pila.
   pop rsi
   pop rdi

   mov rsp, rbp
   pop rbp
   ret


;;;;;  
; Copiar la matriz origen (mOrigin) -segundo parámetro- sobre la matriz
; destino (mDest) -primer parámetro-.
; La matriz origen (mOrigin) no debe modificarse,
; los cambios debe realizarse sobre la matriz destino (mDest).
; Recorrer toda la matriz por filas de izquierda a derecha y de arriba a abajo.
; Para recorrer la matriz en ensamblador el índice va de 0 (posición [0][0])
; a 60 (posición [3][3]) con incrementos de 4 porque los datos son de
; tipo int(DWORD) 4 bytes.
; Esto permitirá copiar dos matrices después de una rotación
; y gestionar la opción '(u)Undo' del juego.
; No se mostrará la matriz.
;
; Variables globales utilizadas:
; Ninguna
;
; Parámetros de entrada:
; (mDest)   :rdi(rdi): Dirección de la matriz donde guardamos los números del juego que queremos sobreescribir.
; (mOrigin) :rsi(rsi): Dirección de la matriz donde guardamos los números del juego que queremos copiar.
;
; Parámetros de salida:
; Ninguno
;;;;;  
copyMatrixP2:
   push rbp
   mov  rbp, rsp
   ;guardamos el estado de los registros del procesador porque
   ;las funciones de C no mantienen el estado de los registros.
   push rdi ;mDest
   push rsi ;mOrigin
   
   
   
   cm_end:
   ;restaurar el estado de los registros que se han guardado en la pila.
   pop rsi
   pop rdi
   
   mov rsp, rbp
   pop rbp
   ret   
   

;;;;;      
; Rotar a la derecha o a la izquierda la matriz (mToRotate) en la dirección 
; indicada por la variable (dir: 'L' izquierda, 'R' derecha) sobre la 
; matriz (mRotated), recibidas como parámetro. 
; Si se rota a la izquierda (dir='L')
; la primera fila pasa a ser la primera columna,
; la segunda fila pasa a ser la segona columna,
; la tercera fila pasa a ser la tercera columna y
; la cuarta fila pasa a ser la cuarta columna.
; Si se gira a la derecha (dir='R')
; la primera columna pasa a ser la cuarta fila,
; la segunda columna pasa a ser la tercera fila,
; la tercera columna pasa a ser la segunda fila y
; la cuarta columna pasa a ser la primera fila.
; En el enunciado se explica en mayor detalle cómo hacer la rotación.
; NOTA: NO es lo mismo que realizar la matriz transpuesta.
; La matriz (mToRotate) no debe modificarse,
; los cambios deben realizarse en la matriz (mRotated).
; Para recorrer la matriz en ensamblador el índice va de 0 (posición [0][0])
; a 60 (posición [3][3]) con incrementos de 4 porque los datos son de
; tipo int(DWORD) 4 bytes.
; Para acceder a una posición concreta de la matriz desde ensamblador
; hay que tener en cuenta que el índice es:(index=(fila*DIMMATRIX+columna)*4),
; multiplicamos por 4 porque los datos son de tipo int(DWORD) 4 bytes.
; Una vez realizada la rotación, copiar la matriz (mRotated) en la matriz
; (mToRotate) recibidas como parámetro llamando a la subrutina copyMatrixP2.
; No se debe mostrar la matriz.
;
; Variables globales utilizadas: Ninguna
; Parámetros de entrada:
; (mToRotate) :rdi(rdi): Dirección de la matriz donde guardamos los números del juego que queremos rotar.
; (dir)       :rsi(sil): Dirección de la rotación: 'La izquierda, 'R' derecha.
;
; Parámetros de salida:
; Ninguno
;;;;;  
rotateMatrixLRP2:
   push rbp
   mov  rbp, rsp
   
   sub  rsp, 64     ;Reservamos espacio para la matriz mRotated: 64 bytes(4*4*4bytes)
   push rbx         ;int mRotated[DIMMATRIX][DIMMATRIX];
   mov  rbx, rsp
   add  rbx, 8      ;rbx: Dirección de la matriz mRotated donde guardaremos los números rotados.
   
   ;guardamos el estado de los registros del procesador porque
   ;las funciones de C no mantienen el estado de los registros.
   push rdi ; mToRotate
   push rsi ; dir
   
   
   
   
   rm_end:
   ;restaurar el estado de los registros que se han guardado en la pila.
   pop rdi
   pop rsi
   
   pop rbx
   
   mov rsp, rbp
   pop rbp
   ret


;;;;;  
; Desplaza los números de cada fila de la matriz (mShift) a la izquierda
; recibida como parámetro, manteniendo el orden de los números y poniendo
; los ceros a la derecha si se desplaza.
; Recorrer la matriz por filas de izquierda a derecha y de arriba a abajo.
; Para recorrer la matriz en ensamblador, el índice va de la
; posición 0 (posición [0][0]) a la 60 (posición [3][3]) con incrementos
; de 4 porque los datos son de tipo int(DWORD) 4 bytes.
; Para acceder a una posición concreta de la matriz desde ensamblador
; hay que tener en cuenta que el índice es:(index=(fila*DIMATRIX+columna)*4),
; multiplicamos por 4 porque los datos son de tipo int(DWORD) 4 bytes.
; En cada fila, si encuentra un 0, mira si hay un número distinto de cero,
; en la misma fila para ponerlo en esa posición.
; Si se desplaza un número (NO LOS CEROS), se deben contar los
; desplazamiento incrementando la variable (shifts).
; Si una fila de la matriz es: [0,2,0,4] y (shifts = 0), quedará
; [2,4,0,0] y (shifts = 2).
; Devolvemos el valor de la variable (shifts) para indicar el número
; de desplazamientos realizados.
; Los cambios deben realizarse sobre la misma matriz.
; No se debe mostrar la matriz.
; Variables globales utilizadas:
; Ninguno
;
; Parámetros de entrada:
; (mShift) :rdi(rdi): Dirección de la matriz donde guardamos los números del juego que queremos desplazar.
;
; Parámetros de salida:
; (shifts) :rax(eax): Desplazamientos realizados.
;;;;;  
shiftNumbersLP2:
   push rbp
   mov  rbp, rsp
   ;guardamos el estado de los registros del procesador porque
   ;las funciones de C no mantienen el estado de los registros.
   push rdi ; mShift
   push rax ; shifts
   
   
   shn_end:                    
   ;restaurar el estado de los registros que se han guardado en la pila.    
   pop rax
   pop rdi
   
   mov rsp, rbp
   pop rbp
   ret


;;;;;  
; Emparejar números iguales de la matriz (mPairs) hacia la izquierda,
; recibida como parámetro, y acumular los puntos en el marcador sumando los
; puntos (p) de las parejas que se hayan hecho.
; Recorrer la matriz por filas de izquierda a derecha y de arriba a abajo.
; Cuando se encuentre una pareja, dos casillas consecutivas de la misma
; fila con el mismo número, juntamos la pareja poniendo la suma de la
; pareja en la casilla de la izquierda, un 0 en la casilla de la derecha y
; acumularemos esta suma en la variable (p) (puntos ganados).
; Si una fila de la matriz es: [4,4,2,2] quedará [8,0,4,0] y
; p = p + (8+4).
; Devolveremos los puntos (p) obtenidos de hacer las parejas.
; Para recorrer la matriz en ensamblador, el índice va de la
; posición 0 (posición [0][0]) a la 60 (posición [3][3]) con incrementos
; de 4 porque los datos son de tipo int(DWORD) 4 bytes.
; Para acceder a una posición concreta de la matriz desde ensamblador
; hay que tener en cuenta que el índice es:(index=(fila*DIMATRIX+columna)*4),
; multiplicamos por 4 porque los datos son de tipo int(DWORD) 4 bytes.
; Los cambios deben realizarse sobre la misma matriz.
; No se debe mostrar la matriz.
;
; Variables globales utilizadas:
; Ninguno
;
; Parámetros de entrada:
; (mPairs) :rdi(edi): Dirección de la matriz donde guardamos los números del juego que queremos hacer parejas.
;
; Parámetros de salida:
; (p)      :rax(eax): Puntos de las parejas que se hayan hecho.
;;;;;  
addPairsLP2:
   push rbp
   mov  rbp, rsp
   ;guardamos el estado de los registros del procesador porque
   ;las funciones de C no mantienen el estado de los registros.
   push rdi ; mPairs
   push rax ; p
   
   
   ap_end:                         ;return p;
   ;restaurar el estado de los registros que se han guardado en la pila.
   pop rax
   pop rdi
   
   mov rsp, rbp
   pop rbp
   ret


;;;;;  
; Verificar si se ha llegado a 2048 o si no se puede realizar algún movimiento.
; Si existe el número 2048 en la matriz (m), cambiar
; el estado a 4 (state=4) para indicar que se ha ganado (WIN!).
; Si no hemos ganado, mirar si se puede realizar algún movimiento,
; Si no se puede realizar ningún movimiento cambiar el estado a 5 (state=5) para
; indicar que se ha perdido (GAME OVER!!!).
; Recorrer la matriz (m) por filas de derecha a izquierda y de abajo arriba
; contando las casillas vacías y mirando si existe el número 2048.
; Si existe el número 2048 poner (status=4) y terminar.
; Si no existe el número 2048 y no hay casillas vacías mirar si se puede
; realizar algún emparejamiento en horizontal o en vertical. Para ello, es necesario
; copiar la matriz (m) sobre la matriz (mAux) llamando la subrutina
; copyMatrixP2, hacer parejas en la matriz (mAux) para mirar si se
; pueden hacer parejas en horizontal llamando a la subrutina addPairsLP2 y
; guardar los puntos obtenidos, rotar la matriz (mAux) llamando la
; subrutina rotateMatrixLRP2 y volver a hacer parejas en la matriz (mAux)
; para mirar si se pueden hacer parejas en vertical llamando a la subrutina
; addPairsLP2 y acumular los puntos obtenidos con los puntos
; obtenidos antes, si los puntos acumulados son 0, significa que no se
; pueden hacer parejas y debe ponerse el estado del juego a 5 (status=5).
; No se puede modificar ni la matriz (m), ni la matriz (mUndo).
;
; Variables globales utilizadas:
; Ninguno
;
; Parámetros de entrada:
; (m)     :rdi(rdi): Dirección de la matriz donde guardamos los números del juego.
; (mAux)  :rsi(rsi): Dirección de la matriz donde copiamos la matriz (m) para comprobarla.
; (state) :rdx(dx) : Estado del juego.
;
; Parámetros de salida:
; (state) :rax(ax) : Estado del juego.
;;;;;  
checkEndP2:
   push rbp
   mov  rbp, rsp
   ;guardamos el estado de los registros del procesador porque
   ;las funciones de C no mantienen el estado de los registros.
   push rdi ; m
   push rsi ; mAux
   push rdx ; state
   
   
   
   che_end:
   ;restaurar el estado de los registros que se han guardado en la pila.
   pop rdx
   pop rsi
   pop rdi
   
   mov rsp, rbp
   pop rbp
   ret   


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Esta subrutina se da hecha. NO SE PUEDE MODIFICAR.
; Mostrar un mensaje debajo del tablero según el valor de la variable
; (state), llamando la función printMessageP2_C.
;
; Variables globales utilizadas: 
; Ninguna 
; 
; Parámetros de entrada: 
; (state) :rdi(di): Estado del juego. 
; 
; Parámetros de salida: 
; Ninguno
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
printMessageP2:
   push rbp
   mov  rbp, rsp
   ;guardamos el estado de los registros del procesador porque
   ;las funciones de C no mantienen el estado de los registros.
   push rbx
   push rcx
   push rdx
   push rsi
   push rdi
   push r8
   push r9
   push r10
   push r11
   push r12
   push r13
   push r14
   push r15
   
   ;Llamamos a la función printMessageP2_C desde ensamblador.
   call printMessageP2_C

   ;restaurar el estado de los registros que se han guardado en la pila.
   pop r15
   pop r14
   pop r13
   pop r12
   pop r11
   pop r10
   pop r9
   pop r8
   pop rdi
   pop rsi
   pop rdx
   pop rcx
   pop rbx
   
   mov rsp, rbp
   pop rbp
   ret 
   
   
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Esta subrutina se da hecha. NO SE PUEDE MODIFICAR.
; Generar un nueva ficha aleatoriamente.
; Si hay al menos una celda vacía en la matriz (m), generar una fila y
; una columna aleatoriamente hasta que sea una de las celdas vacías. 
; Luego, generar un número aleatorio para decidir si el nuevo mosaico 
; debe ser un 2 (90% de los casos) o un 4 (10% de los casos),
; llamando a la función insertTileP2_C.
; 
; Variables globales utilizadas: 
; Ninguna 
; 
; Parámetros de entrada: 
; (m) :rdi(edi): Dirección de la matriz donde guardamos los números del juego. 
; 
; Parámetros de salida: 
; Ninguno
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
insertTileP2:
   push rbp
   mov  rbp, rsp
   ;guardamos el estado de los registros del procesador porque
   ;las funciones de C no mantienen el estado de los registros.
   push rax
   push rbx
   push rcx
   push rdx
   push rsi
   push rdi
   push r8
   push r9
   push r10
   push r11
   push r12
   push r13
   push r14
   push r15
   
   ; Llamamos a la función insertTileP2_C desde ensamblador.
   call insertTileP2_C
  
   ;restaurar el estado de los registros que se han guardado en la pila.
   pop r15
   pop r14
   pop r13
   pop r12
   pop r11
   pop r10
   pop r9
   pop r8
   pop rdi
   pop rsi
   pop rdx
   pop rcx
   pop rbx
   pop rax
   
   mov rsp, rbp
   pop rbp
   ret 
   
   
;;;;;
; Esta subrutina se da hecha. NO SE PUEDE MODIFICAR
; Juego de 2048
; Subrutina principal del juego
; Permite jugar al juego del 2048 llamando a todas las funcionalidades.
;
; Pseudo código:
; Inicializar estado del juego, (state=1).
; Mostrar el tablero de juego (llamar la subrutina printBoardP2).
; Actualiza el contenido del Tablero de Juego y los puntos realizados
; (llamar la subrutina updateBoardP2).
; Mientras (state==1) hacer:
;   Copiar la matriz (m) sobre la matriz (mAux) (llamando la subrutina
;   copyMatrixP2) y copiar los puntos (score) sobre (scoreAux).
;   Leer una tecla (c) llamando a la subrutina getchP2.
;   Según la tecla leída llamaremos a las subrutinas correspondientes.
;     ['i' (arriba),'j'(izquierda),'k' (abajo) o 'l'(derecha)]
;     Desplazar los números y realizar las parejas según la dirección elegida.
;     Según la tecla pulsada, rotar la matriz (m) gritando la subrutina
;     rotateMatrixLRP2, para poder realizar los desplazamientos de los
;     números llamando a la subrutina shiftNumbersLP2 y hacer las parejas
;     llamando la subrutina addPairsLP2 que devuelve los puntos que debemos
;     sumar en el marcador (score).
;     Volver a desplazar los números llamando a la subrutina shiftNumbersLP2
;     con las parejas hechas.
;     Si se ha realizado algún desplazamiento o alguna pareja
;     indicarlo poniendo (state=2).
;     Luego, volver a rotando llamando la subrutina rotateMatrixLRP2
;     para dejar la matriz en la posición inicial.
;     Para la tecla 'j' (izquierda) y no es necesario realizar rotaciones,
;     para el resto se realizarán rotaciones.
;     'u' Poner (state = 3) para recuperar el estado anterior.
;     '<ESC>' (ASCII 27) poner (state = 0) para salir del juego.
;   Si no es ninguna de estas teclas no hacer nada.
;   Actualizar el marcador (score) con el punto de las parejas hechas
;   (score = score + p)
;   Si hemos desplazado algún número o hemos hecho alguna pareja
;   (((s1>0) || (p>0) || (s2>0)))), copiar el estado del juego que hemos
;   guardado en (mAux y scoreAux) en (mUndo y scoreUndo) para poder hacer
;   el Undo (recuperar estado anterior) copiando (mAux) sobre (mUndo)
;   llamando a la subrutina copyMatrixP2 y haciendo (scoreUndo = scoreAux).
;   Generar una nueva ficha llamando a la subrutina insertTileP2 y poner
;   la variable state a 1 (state=1).
;   Actualiza el contenido del Tablero de Juego y los puntos realizados
;   llamando a la subrutina updateBoardP2.
;   Verificar si se ha llegado a 2048 o si se pueden realizar movimientos
;   llamando la subrutina CheckEndP2.
;   Mostrar un mensaje debajo del tablero según el valor de la variable
;   (state) llamando a la subrutina printMessageP2
;   Si debemos recuperar el estado anterior (state=3), copiar el estado
;   anterior del juego que tenemos en (mUndu y scoreUndu) sobre (m y score)
;   llamando a la subrutina copyMatrixP2 y haciendo (score = scoreUndo),
;   poner la variable state a 1 (state=1) y actualiza el contenido
;   del Tablero de Juego y los puntos que se han hecho llamando a la subrutina
;   updateBoardP2.
; Fino mientras.
; Salir:
; Se acabó el juego.
;
; Variables globales utilizadas:
; Ninguna
;
; Parámetros de entrada:
; (m)     :rdi(rdi): Dirección de la matriz donde guardamos los números del juego.
; (mAux)  :rsi(rsi): Dirección de la matriz donde copiamos la matriz (m) para comprobarla.
; (mUndu) :rdx(rdx): Dirección de la matriz donde guardamos el último movimiento para poder volver atrás.
;
; Parámetros de salida:
; Ninguno
;;;;;  
playP2:
   push rbp
   mov  rbp, rsp
   ;guardamos el estado de los registros del procesador porque
   ;las funciones de C no mantienen el estado de los registros.
   push rax
   push rbx
   push rcx
   push rdx
   push rsi
   push rdi
   push r8
   push r9
   push r10
   push r11
   push r12
   push r13
   push r14
   push r15
   
   push rdi
   push rsi
   push rdx
   call printBoardP2_C        ;printBoardP2_C();
   pop  rdx
   pop  rsi
   pop  rdi
   
   mov  bx, 1                 ;state = 1;
   
   mov  r8, rdi               ;m
   mov  r9, rsi               ;mAux
   mov  r10, 290500           ;int score     = 290500;
   mov  r11, 0                ;int scoreAux  = 0;
   mov  r12, 1                ;int scoreUndu = 1;
   
   mov  rsi, r10
   call updateBoardP2         ;updateBoardP2_C(m, score);

   p_loop:                    ;while  {  
   cmp  bx, 1                 ;(state == 1)
   jne  p_endloop
      
     mov  rdi, r9
     mov  rsi, r8  
     call copyMatrixP2        ;copyMatrixP2_C(mAux,m);
     mov  r11, r10            ;scoreAux = score
        
     call getchP2             ;getchP2_C();
     
     mov  r13, 0              ;s1 = 0;
     mov  r14, 0              ;p  = 0;
     mov  r15, 0              ;s2 = 0;
     
     p_i:
     cmp al, 'i'              ;i:(105) up
     jne  p_j
       mov  rdi, r8
       mov  sil, 'L'
       call rotateMatrixLRP2   ;rotateMatrixLRP2_C(m,'L');
      
       call shiftNumbersLP2   ;s1 = shiftNumbersLP2_C(m);
       mov  r13d, eax
       call addPairsLP2       ;p  = addPairsLP2_C(m);
       mov  r14d, eax
       call shiftNumbersLP2   ;s2 = shiftNumbersLP2_C(m);
       mov  r15d, eax           
      
       mov  sil, 'R'
       call rotateMatrixLRP2   ;rotateMatrixLRP2_C(m,'R');
       jmp  p_moved
      
     p_j:
     cmp al, 'j'              ;j:(106) left
     jne  p_k
       mov  rdi, r8
       call shiftNumbersLP2   ;s1 = shiftNumbersLP2_C(m);
       mov  r13d, eax
       call addPairsLP2       ;p  = addPairsLP2_C(m);
       mov  r14d, eax
       call shiftNumbersLP2   ;s2 = shiftNumbersLP2_C(m);
       mov  r15d, eax           
      
      jmp  p_moved
   
     p_k:
     cmp al, 'k'              ;k:(107) down
     jne  p_l
       mov  rdi, r8
       mov  sil, 'R'
       call rotateMatrixLRP2   ;rotateMatrixLRP2_C(m,'R');
      
       call shiftNumbersLP2   ;s1 = shiftNumbersLP2_C(m);
       mov  r13d, eax
       call addPairsLP2       ;p  = addPairsLP2_C(m);
       mov  r14d, eax
       call shiftNumbersLP2   ;s2 = shiftNumbersLP2_C(m);
       mov  r15d, eax           
      
       mov  sil, 'L'
       call rotateMatrixLRP2   ;rotateMatrixLRP2_C(m,'L');
       jmp  p_moved
      
     p_l:
     cmp al, 'l'              ;l:(108) right
     jne  p_u
       mov  rdi, r8
       mov  sil, 'R'
       call rotateMatrixLRP2   ;rotateMatrixLRP2_C(m,'R');
       call rotateMatrixLRP2   ;rotateMatrixLRP2_C(m,'R');
      
       call shiftNumbersLP2   ;s1 = shiftNumbersLP2_C(m);
       mov  r13d, eax
       call addPairsLP2       ;p  = addPairsLP2_C(m);
       mov  r14d, eax
       call shiftNumbersLP2   ;s2 = shiftNumbersLP2_C(m);
       mov  r15d, eax           
      
       mov  sil, 'L'
       call rotateMatrixLRP2   ;rotateMatrixLRP2_C(m,'L');
       call rotateMatrixLRP2   ;rotateMatrixLRP2_C(m,'L');
       jmp  p_moved
      
     p_u:
     cmp al, 'u'              ;u:(117) Undu
     jne  p_ESC
       mov bx, 3              ;state = 3;
       jmp  p_moved
   
     p_ESC:
     cmp al, 27               ;ESC:(27) exit
     jne p_moved
       mov bx, 0              ;state=0;d 

     p_moved:
     movsxd r14, r14d         ;score = score + (long)p;
     add r10, r14
   
     cmp  r13d, 0             ;if ( (s1>0) || 
     jg  p_status2
       cmp  r14d, 0           ;(p>0) || 
       jg  p_status2
         cmp r15d, 0          ;(s2>0) ) 
         jle p_endifstatus2
           p_status2:
           mov  rdi, rdx
           mov  rsi, r9
           call copyMatrixP2  ;copyMatrixP2_C(mUndo,mAux);
           mov  r12, r11      ;scoreUndo = scoreAux
           mov  rdi, r8
           call insertTileP2  ;insertTileP2_C(m); 
           mov  bx, 1         ;state = 1;
     p_endifstatus2:   
   
     mov  rdi, r8
     mov  rsi, r10
     call updateBoardP2       ;updateBoardP2_C(m, score);
     mov  rsi, r9
     push rdx
     mov  dx, bx
     call checkEndP2          ;state = checkEndP2_C(m, mAux, state);
     mov  bx, ax
     pop  rdx
     mov  di, ax
     call printMessageP2      ;state = printMessageP2_C(state);
     mov  bx, ax
     
     cmp bx, 3                ;(state == 3) 
     jne p_endifstatus3
       mov  rdi, r8
       mov  rsi, rdx
       call copyMatrixP2      ;copyMatrixP2_C(m,mUndo);
       mov  r10, r12          ;score = scoreUndo;
       mov  bx, 1             ;state = 1;
       mov  rdi, r8
       mov  rsi, r10 
       call updateBoardP2     ;updateBoardP2_C(m, score);
     p_endifstatus3:
   
     jmp p_loop
   p_endloop:
   
   p_end:       
   ;restaurar el estado de los registros que se han guardado en la pila.
   pop r15
   pop r14
   pop r13
   pop r12
   pop r11
   pop r10
   pop r9
   pop r8
   pop rdi
   pop rsi
   pop rdx
   pop rcx
   pop rbx
   pop rax  
   
   mov rsp, rbp
   pop rbp
   ret

