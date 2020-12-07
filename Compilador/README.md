

## Como ejecutarlo
1. Agregar el archivo `lib/cup/java-cup-11b-runtime.jar` al proyecto como biblioteca.

2. Ejecutar el script `run.bat`

3. Ejecutar el método `main` de la clase `Main.java` 

4. Pegar el Final.asm en la carpeta de TASM

5. Con el DOSBox usar las instrucciones que están en EA3.bat :

   - tasm numbers.asm
   - tasm Final.asm
   - tlink /3 Final.obj numbers.obj /v /s /main
   - Final.exe
   
   