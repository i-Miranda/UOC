section .note.GNU-stack noalloc noexec nowrite progbits
section .data               
;Cambiar Nombre y Apellido por vuestros datos.
developer db "_Nombre_ _Apellido1_",0

;Constante que también está definida en C.
DIMMATRIX    equ 4      
SIZEMATRIX   equ DIMMATRIX*DIMMATRIX ;=16

section .text        
;Variables definidas en ensamblador.
global developer     
                         
;Subrutinas de ensamblador que se llaman desde C.
global showNumberP1, updateBoardP1, copyMatrixP1, 
global rotateMatrixLRP1, shiftNumbersLP1, addPairsLP1
global playP1

;Variables globales definidas en C.
extern rowScreen, colScreen, charac, state
extern m, mRotated, number, score, dir

;Funciones de C que se llaman desde ensamblador.
extern clearScreen_C, gotoxyP1_C, getchP1_C, printchP1_C
extern printBoardP1_C, insertTileP1_C, printMessageP1_C


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; ATENCIÓN: Recordad que en ensamblador las variables y los parámetros 
;;   de tipo 'char' deben asignarse a registros de tipo  
;;   BYTE (1 byte): al, ah, bl, bh, cl, ch, dl, dh, sil, dil, ..., r15b
;;   los de tipo 'short' deben asignarse a registros de tipo 
;;   WORD (2 bytes): ax, bx, cx, dx, si, di, ...., r15w
;;   los de tipo 'int' deben asignarse a registros de tipo 
;;   DWORD (4 bytes): eax, ebx, ecx, edx, esi, edi, ...., r15d
;;   los de tipo 'long' deben asignarse a registros de tipo 
;;   QWORD (8 bytes): rax, rbx, rcx, rdx, rsi, rdi, ...., r15
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Las subrutinas en ensamblador que debéis implementar son:
;;   showNumberP1, updateBoardP1, copyMatrixP1,  
;;   rotateMatrixLRP1, shiftNumbersLP1, addPairsLP1.
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Esta subrutina se proporciona ya hecha. NO LA PODÉIS MODIFICAR.
; Posicionar el cursor en la fila indicada por la variable (rowScreen) y 
; en la columna indicada por la variable (colScreen) de la pantalla,
; llamando a la función gotoxyP1_C.
; 
; Variables globales utilizadas:   
; (rowScreen): Fila de la pantalla donde posicionamos el cursor.
; (colScreen): Columna de la pantalla donde posicionamos el cursor.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
gotoxyP1:
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

   call gotoxyP1_C
 
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


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Esta subrutina se proporciona ya hecha. NO LA PODÉIS MODIFICAR.
; Mostrar un carácter guardado en la variable (charac) en la pantalla, 
; en la posición donde está el cursor, llamando a la función printchP1_C.
; 
; Variables globales utilizadas:   
; (charac): Carácter que queremos mostrar.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
printchP1:
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

   call printchP1_C
 
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
   

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Esta subrutina se proporciona ya hecha. NO LA PODÉIS MODIFICAR.
; Leer una tecla y guardar el carácter asociado en la variable (charac)
; sin mostrarlo en pantalla, llamando a la función getchP1_C. 
; 
; Variables globales utilizadas:   
; (charac): Carácter que leemos de teclado.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
getchP1:
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

   call getchP1_C
 
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
; Convierte el número de la variable (number) de tipo long de 6 dígitos
; (0 <= nuberm <= 999999) a caracteres ASCII que representan su valor.
; Si (num) es menor que 0 cambiamos el valor a 0,
; si es mayor que 999999, cambiaremos el valor a 999999.
; Se debe dividir(/) el valor entre 10, de forma iterativa,
; hasta obtener el 6 dígitos.
; En cada iteración, el residuo de la división (%) que es un valor
; entre (0-9) indica el valor del dígito a convertir
; a ASCII ('0' - '9') sumando '0' (48 decimal) para poder mostrarlo.
; Cuando el cociente sea 0 mostraremos espacios en la parte no significativa.
; Por ejemplo, si (number)=103 mostraremos "   103" y no "000103".
; Se deben mostrar los dígitos (carácter ASCII) desde la posición
; indicada por las variables (rowScreen) y (colScreen)
; posición de las unidades, hacia la izquierda.
; El primer dígito que obtenemos son las unidades, después las decenas,
; ..., para mostrar el valor se debe desplazar el cursor una posición
; a la izquierda en cada iteración.
; Para posicionar el cursor se llama a la subrutina gotoxyP1 y para
; mostrar los caracteres en la subrutina printchP1.
;
; Variables globales utilizadas:
; (number)   : Número que queremos mostrar.
; (rowScreen): Fila de la pantalla donde posicionamos el cursor.
; (colScreen): Columna de la pantalla donde posicionamos el cursor.
; (charac)   : Carácter a escribir en pantalla.
;;;;;
showNumberP1:
   push rbp
   mov  rbp, rsp
   ;guardar el estado de los registros que se modifican en esta 
   ;subrutina y que no se utilizan para devolver valores.
   
   
  
   sn_end: 
   ;restaurar el estado de los registros que se han guardado en la pila.
   
   mov rsp, rbp
   pop rbp
   ret


;;;;;
; Actualizar el contenido del Tablero de Juego con los datos de la
; matriz (m) de 4x4 de tipo int y los puntos del marcador
; (score) que se han hecho.
; Se debe recorrer toda la matriz (m), y para cada elemento de la matriz
; posicionar el cursor en la pantalla y mostrar el número de aquella
; posición de la matriz.
; Recorrer toda la matriz por filas de izquierda a derecha y de arriba a abajo.
; Para recorrer la matriz en ensamblador el índice va de 0 (posición [0][0])
; a 60 (posición [3][3]) con incrementos de 4 porque los datos son de
; tipo int(DWORD) 4 bytes.
; Luego, mostrar el marcador (score) en la parte inferior del tablero,
; fila 18, columna 35 llamando la subrutina showNumberP1.
; Por último posicionar el cursor en la fila 18, columna 36 llamando la
; subrutina gotoxyP1.
;
; Variables globales utilizadas:
; (rowScreen): Fila de la pantalla donde posicionamos el cursor.
; (colScreen): Columna de la pantalla donde posicionamos el cursor.
; (m)        : Matriz donde guardamos los números del juego.
; (Score)    : Puntos acumulados en el marcador.
; (number)   : Número que queremos mostrar.
;;;;;  
updateBoardP1:
   push rbp
   mov  rbp, rsp
   ;guardar el estado de los registros que se modifican en esta 
   ;subrutina y que no se utilizan para devolver valores.
   
   
   
   ub_end:
   ;restaurar el estado de los registros que se han guardado en la pila.
   
   mov rsp, rbp
   pop rbp
   ret


;;;;;  
; Copiar los valores de la matriz (mRotated) en la matriz (m).
; La matriz (mRotated) no debe modificarse,
; los cambios deben realizarse en la matriz (m).
; Recorrer toda la matriz por filas de izquierda a derecha y de arriba a abajo.
; Para recorrer la matriz en ensamblador el índice va de 0 (posición [0][0])
; a 60 (posición [3][3]) con incrementos de 4 porque los datos son de
; tipo int(DWORD) 4 bytes.
; No se mostrará la matriz.
;
; Variables globales utilizadas:
; (m)       : Matriz donde guardamos los números del juego.
; (mRotated): Matriz con los números rotados.
;;;;;  
copyMatrixP1:
   push rbp
   mov  rbp, rsp
   ;guardar el estado de los registros que se modifican en esta 
   ;subrutina y que no se utilizan para devolver valores.
   
   
   
   cm_end:
   ;restaurar el estado de los registros que se han guardado en la pila.
      
   mov rsp, rbp
   pop rbp
   ret


;;;;;      
; Rotar a la derecha o a la izquierda la matriz (m) en la dirección
; indicada por la variable (dir: 'L' izquierda, 'R' derecha) sobre la
; matriz (mRotated).
; Si se rota a la izquierda (dir='L')
; la primera fila pasa a ser la cuarta columna,
; la segunda fila pasa a ser la tercera columna,
; la tercera fila pasa a ser la segunda columna y
; la cuarta fila pasa a ser la primera columna.
; Si se rota a la derecha (dir='R')
; la primera columna pasa a ser la cuarta fila,
; la segunda columna pasa a ser la tercera fila,
; la tercera columna pasa a ser la segunda fila y
; la cuarta columna pasa a ser la primera fila.
; En el enunciado se explica en mayor detalle cómo hacer la rotación.
; NOTA: NO es lo mismo que realizar la matriz transpuesta.
; La matriz (m) no debe modificarse,
; los cambios deben realizarse en la matriz (mRotated).
; Para recorrer la matriz en ensamblador el índice va de 0 (posición [0][0])
; a 60 (posición [3][3]) con incrementos de 4 porque los datos son de
; tipo int(DWORD) 4 bytes.
; Para acceder a una posición concreta de la matriz desde ensamblador
; hay que tener en cuenta que el índice es:(index=(fila*DIMMATRIX+columna)*4),
; multiplicamos por 4 porque los datos son de tipo int(DWORD) 4 bytes.
; Una vez realizada la rotación, copiar la matriz (mRotated) en la matriz
; (m) llamando a la subrutina copyMatrixP1.
; No se debe mostrar la matriz.
;
; Variables globales utilizadas:
; (m)       : Matriz donde guardamos los números del juego.
; (mRotated): Matriz con los números rotados.
; (dir)     : Dirección de la rotación: 'L' izquierda, 'R' derecha.
;;;;;  
rotateMatrixLRP1:
   push rbp
   mov  rbp, rsp
   ;guardar el estado de los registros que se modifican en esta 
   ;subrutina y que no se utilizan para devolver valores.
   
   
   
   rm_end:
   ;restaurar el estado de los registros que se han guardado en la pila.
      
   mov rsp, rbp
   pop rbp
   ret


;;;;;  
; Desplaza los números de cada fila de la matriz (m) a la izquierda
; manteniendo el orden de los números y
; poniendo los ceros a la derecha si se desplaza.
; Recorrer la matriz por filas de izquierda a derecha y de arriba a abajo.
; Para recorrer la matriz en ensamblador, el índice va de la
; posición 0 (posición [0][0]) a la 60 (posición [3][3]) con incrementos
; de 4 porque los datos son de tipo int(DWORD) 4 bytes.
; Para acceder a una posición concreta de la matriz desde ensamblador
; hay que tener en cuenta que el índice es:(index=(fila*DIMATRIX+columna)*4),
; multiplicamos por 4 porque los datos son de tipo int(DWORD) 4 bytes.
; Si se desplaza un número (NO LOS CEROS), se deben contar los
; desplazamiento incrementando la variable (shifts).
; En cada fila, si encuentra un 0, mira si hay un número distinto de cero,
; en la misma fila para ponerlo en esa posición.
; Si una fila de la matriz es: [0,2,0,4], quedará
; [2,4,0,0] y (state = 2).
; Los cambios deben realizarse sobre la misma matriz.
; No se debe mostrar la matriz.
;
; Variables globales utilizadas:
; (m)    : Matriz donde guardamos los números del juego.
; (state): Estado del juego. (2: Se han hecho movimientos).
;;;;;  
shiftNumbersLP1:
   push rbp
   mov  rbp, rsp
   ;guardar el estado de los registros que se modifican en esta 
   ;subrutina y que no se utilizan para devolver valores.
   
   
   
   shn_end:
   ;restaurar el estado de los registros que se han guardado en la pila.
      
   mov rsp, rbp
   pop rbp
   ret
      

;;;;;  
; Emparejar números de la matriz (m) hacia la izquierda y acumular los
; puntos (p) en el marcador sumando los puntos de las parejas que se hayan hecho.
; Recorrer la matriz por filas de izquierda a derecha y de arriba a abajo.
; Cuando se encuentre una pareja, dos casillas consecutivas de la misma
; fila con el mismo número, juntamos la pareja poniendo la suma de la
; pareja en la casilla de la izquierda, un 0 en la casilla de la derecha y
; acumularemos esta suma en la variable (p) (puntos ganados).
; Si una fila de la matriz es: : [4,4,2,2] quedará [8,0,4,0] y
; p = p + (8+4).
; Si al final se ha juntado alguna pareja (p>0), pondremos la variable
; (state) a 2 para indicar que se ha movido algún número y actualizaremos
; la variable (score) con los puntos obtenidos de realizar las parejas.
; Para recorrer la matriz en ensamblador, en este caso, el índice va de la
; posición 0 (posición [0][0]) a la 60 (posición [3][3]) con incrementos de
; 4 porque los datos son de tipo int(DWORD) 4 bytes.
; Para acceder a una posición concreta de la matriz desde ensamblador
; hay que tener en cuenta que el índice es:(index=(fila*DimMatrix+columna)*4),
; multiplicamos por 4 porque los datos son de tipo int(DWORD) 4 bytes.
; Los cambios deben realizarse sobre la misma matriz.
; No se debe mostrar la matriz.
;
; Variables globales utilizadas:
; (m)    : Matriz donde guardamos los números del juego.
; (score): Puntos acumulados en el marcador.
; (state): Estado del juego. (2: Se han hecho movimientos).
;;;;;  
addPairsLP1:
   push rbp
   mov  rbp, rsp
   ;guardar el estado de los registros que se modifican en esta 
   ;subrutina y que no se utilizan para devolver valores.
   
   
   
   ap_end:                         
   ;restaurar el estado de los registros que se han guardado en la pila.
   
   mov rsp, rbp
   pop rbp
   ret
   
   
;;;;;
; Juego del 2048
; Subrutina principal del juego
; Permite jugar al juego del 2048 llamando a todas las funcionalidades.
; Esta subrutina se da hecha. NO SE PUEDE MODIFICAR.
;
; Pseudo código:
; Inicializar estado del juego, (state=1)
; Mostrar el tablero de juego (llamar la función printBoardP1).
; Actualiza el contenido del Tablero de Juego y los puntos realizados
; (llamar la subrutina updateBoardP1).
; Mientras (state==1) hacer
;   Leer una tecla (charac) llamando a la subrutina getchP1.
;   Según la tecla leída llamaremos a las subrutinas correspondientes.
;     ['i' (arriba),'j'(izquierda),'k' (abajo) o 'l'(derecha)]
;     Rotar la matriz (m) llamando la subrutina rotateMatrixLRP1, para
;     poder realizar los desplazamientos de los números a la izquierda llamando la
;     subrutina shiftNumbersLP1 y hacer las parejas llamando a la subrutina
;     addPairsLP1 que también actualiza los puntos que se han sumado al
;     marcador (score), volver a desplazar los números a la izquierda
;     llamando a la subrutina shiftNumbersLP1 con las parejas hechas.
;     Si se ha realizado algún desplazamiento o alguna pareja (state=2).
;     Luego, volver a rotar gritando la subrutina rotateMatrixLRP1
;     para dejar la matriz en la posición inicial.
;     Para la tecla 'j' (izquierda) no es necesario realizar rotaciones,
;     para el resto se realizarán rotaciones.
;     '<ESC>' (ASCII 27) poner (state = 0) para salir del juego.
;   Si no es ninguna de estas teclas no hacer nada.
;   Si se ha realizado algún desplazamiento o alguna pareja (state==2), generar
;   una nueva ficha llamando a la subrutina insertTileP1_C y poner
;   la variable state a 1 (state=1).
;   Actualiza el contenido del Tablero de Juego y los puntos realizados
;   llamar a la subrutina updateBoardP1.
; Fin mientras.
; Mostrar un mensaje debajo del tablero según el valor de la variable
; (state) llamando a la función printMessageP1_C.
; Salir:
; Se acaba el juego.
;
; Variables globales utilizadas:
; (charac): Carácter que leemos de teclado.
; (state) : Estado del juego.
;           0 : Salir, hemos pulsado la tecla 'ESC').
;           1 : Continuamos jugando.
;           2 : Continuamos jugando pero se han hecho cambios en la matriz.
; (dir)   : Dirección de la rotación: 'L' izquierda, 'R' derecha.
;;;;;  
playP1:
   push rbp
   mov  rbp, rsp
   ;guardar el estado de los registros que se modifican en esta 
   ;subrutina y que no se utilizan para devolver valores.
   push rax
   
   mov BYTE[state], 1         ;state = 1;    
   
   call printBoardP1_C        ;printBoardP1_C();
   call updateBoardP1         ;updateBoardP1_C(); 

   playP1_Loop:               ;while    
   cmp  BYTE[state], 1        ;(state == '1') {
   jne  playP1_End
      
     call getchP1             ;getchP1_C();
     mov al, BYTE[charac] 
     p_i:
     cmp al, 'i'              ;i:(105) up
     jne  p_j
       mov  BYTE[dir], 'L'    ;dir = 'L';
       call rotateMatrixLRP1  ;rotateMatrixLRP1_C();
      
       call shiftNumbersLP1   ;shiftNumbersLP1_C();
       call addPairsLP1       ;addPairsLP1_C();
       call shiftNumbersLP1   ;shiftNumbersLP1_C();
       
       mov  BYTE[dir], 'R'    ;dir = 'R';
       call rotateMatrixLRP1  ;rotateMatrixLRP1_C();
       jmp  p_moved           ;break;
      
     p_j:
     cmp al, 'j'              ;j:(106) left
     jne  p_k
       call shiftNumbersLP1   ;shiftNumbersLP1_C();
       call addPairsLP1       ;addPairsLP1_C();
       call shiftNumbersLP1   ;shiftNumbersLP1_C();
       jmp  p_moved           ;break;
   
     p_k:
     cmp al, 'k'              ;k:(107) down
     jne  p_l
       mov  BYTE[dir], 'R'    ;dir = 'R';
       call rotateMatrixLRP1  ;rotateMatrixLRP1_C();
      
       call shiftNumbersLP1   ;shiftNumbersLP1_C();
       call addPairsLP1       ;addPairsLP1_C();
       call shiftNumbersLP1   ;shiftNumbersLP1_C();
       
       mov  BYTE[dir], 'L'    ;dir = 'L';
       call rotateMatrixLRP1  ;rotateMatrixLRP1_C();
       jmp  p_moved           ;break;
      
     p_l:
     cmp al, 'l'              ;l:(108) right
     jne  p_ESC
       mov  BYTE[dir], 'R'    ;dir = 'R';
       call rotateMatrixLRP1  ;rotateMatrixLRP1_C();
       call rotateMatrixLRP1  ;rotateMatrixLRP1_C();
      
       call shiftNumbersLP1   ;shiftNumbersLP1_C();
       call addPairsLP1       ;addPairsLP1_C();
       call shiftNumbersLP1   ;shiftNumbersLP1_C();
       
       mov  BYTE[dir], 'L'    ;dir = 'L';
       call rotateMatrixLRP1  ;rotateMatrixLRP1_C();
       call rotateMatrixLRP1  ;rotateMatrixLRP1_C();
       jmp  p_moved           ;break;
      
     p_ESC:
     cmp al, 27               ;ESC:(27) exit
     jne p_moved
       mov BYTE[state], 0     ;state=0; 

     p_moved:

     cmp BYTE[state], 2       ;if(state == 2);
     jne playP1_Next 
       call insertTileP1_C    ;insertTileP1_C(); 
       mov BYTE[state], 1     ;state = '1';
     playP1_Next:
     call updateBoardP1      ;updateBoardP1_C();
      
   jmp playP1_Loop
   
   call printMessageP1_C      ;printMessageP1_C();
   
   playP1_End:
   ;restaurar el estado de los registros que se han guardado en la pila.
   pop rax
   
   mov rsp, rbp
   pop rbp
   ret
