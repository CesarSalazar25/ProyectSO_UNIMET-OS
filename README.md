# Proyecto II - Detección y Predicción de Interbloqueos


Usted ha sido contratado como parte del equipo de desarrollo del sistema operativo “UNIMET OS”. En este momento, el equipo se encuentra debatiendo qué medidas debe tomar el sistema en contra del interbloqueo. Por ello, se le ha encardo programar simulaciones de la técnica de predicción de interbloqueo con negación de asignación y la técnica de detección de interbloqueo. Para esto, debe realizar un programa en Java que simule ambas técnicas, recibiendo las mismas entradas, permitiendo conocer los resultados de ambos métodos a la vez.


Cuando se inicie el programa, se deberá poder especificar los recursos del sistema, el cual puede tener distintos tipos de recursos, todos reutilizables; con cantidades diferentes para cada uno. Una vez introducidos estos datos, se iniciarán las simulaciones. 
Una vez iniciadas las simulaciones, el programa debe permitir introducir procesos en el sistema, requiriendo para ello que se introduzcan sus requerimientos máximos de recursos. Una vez creado el proceso, en cualquier momento debe ser posible realizar solicitudes de recursos para un proceso específico, siempre y cuando estas no violen los requerimientos máximos indicados anteriormente. 


Cuando la solicitud de un proceso es rechazada, este pasará a un estado de bloqueado, por lo que no podrá realizar nuevas solicitudes, solo repetir la solicitud que lo llevó a bloquearse hasta que esta sea aceptada. Cuando un proceso ha cubierto sus requisitos máximos, debe habilitarse la opción de finalizar con éxito su ejecución, lo que liberará los recursos que este tenía asignados y eliminará al proceso del sistema, impidiendo que genere nuevas solicitudes. 


Note que al utilizar dos técnicas distintas, a pesar de haber sido creado en los dos sistemas y realizar las mismas solicitudes, un proceso puede estar habilitado en una simulación pero estar eliminado o bloqueado en la otra. En este caso, debe permitirse crear solicitudes para este proceso, pero estas deberán ser ignoradas por la simulación en la que el proceso no tenga permiso de realizar solicitudes.


En todo momento se debe conocer los procesos que se encuentren en sistema y su estado. Además, el sistema  debe llevar registro de las siguientes estadísticas donde apliquen:

*Número de procesos creados.

*Número de procesos en sistema.

*Número de solicitudes realizadas.

*Tiempo acumulado, en milisegundos, que ha dedicado a ejecutar los algoritmos de detección, recuperación o predicción del interbloqueo.

*Número de procesos bloqueados en el momento.

*Número total de procesos bloqueados.

*Número total de procesos eliminados.

Sobre el método de predicción de interbloqueo:

La simulación de predicción de interbloqueo utilizará la técnica de negación de asignación de recursos y el algoritmo del banquero. 
En el anexo 1 se presenta un extracto del capítulo 6 del libro Operating Systems de William Stallings en su 6ta edición, donde se describe en detalle el algoritmo de predicción de interbloqueo con negación de asignación de recursos. 

Sobre el método de detección de interbloqueo:

La simulación de detección de interbloque debe, para la recuperación de este, abortar todos los procesos involucrados en el interbloqueo. La detección se realizará después de cada asignación de un recurso. 
En el anexo 2 se presenta un extracto del capítulo 6 del libro Operating Systems de William Stallings en su 6ta edición, donde se describe en detalle el algoritmo de detección de interbloqueo.

Aspectos importantes para la entrega:

*Se debe de poder compilar el proyecto entregado, sin requerir ninguna modificación.

*Debe hacer uso de interfaz gráfica para cualquier entrada solicitada y para mostrar la información requerida.
