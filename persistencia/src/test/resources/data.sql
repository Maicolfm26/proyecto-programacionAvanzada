--Se crean las ciudades en la base de datos
insert into Ciudad values("123","Armenia");
insert into Ciudad values("124", "Pereira");
insert into Ciudad values("125", "Medellin");
insert into Ciudad values("126", "Bogota");
insert into Ciudad values("127", "Cali");

--Se crean las categorias en la base de datos como datos de prueba
insert into Categoria values(1, "Ropa");
insert into Categoria values(2, "Tenis");
insert into Categoria values(3, "Jeans");
insert into Categoria values(4, "Carros");
insert into Categoria values(5, "Alcohol");

--Se crean algunos usuarios en la base de datos
insert into Usuario values("100765489", "carlos@gmail.com", "Carlos Florez", "123", "123");
insert into Usuario values("42785998", "maria@gmail.com", "Maria Zapata", "1130", "124");
insert into Usuario values("13838543", "antonio@gmail.com", "Antonio Lopez", "1232", "127");
insert into Usuario values("98765554", "gisela@gmail.com", "Gisela Carvajal", "0000", "125");
insert into Usuario values("3212345", "mariana@gmail.com", "Mariana Carabali", "1111", "126");

--Se insertan algunos productos en la base de datos
insert into Producto values(1, "Camiseta de tela fria", null, "2021-11-11", "Camisa", 20000, 5, "123", "100765489"  );
insert into Producto values(2, "Jean jogger", null, "2021-10-22", "Jogger", 30000, 15, "124", "42785998"  );
insert into Producto values(3, "Tenis af1 blancos", null, "2021-12-22", "AF1", 130000, 3, "127", "13838543"  );

--Se insertan algunos productos y categoria en la tabla categori_productos
insert into Categoria_productos values(1, 1 );
insert into Categoria_productos values(1, 2 );
insert into Categoria_productos values(1, 3 );
insert into Categoria_productos values(2, 3 );


--Se insertan algunas imagenes a la base de datos
insert into Producto_imagenes values(1, "C:\Users\tavog\imagen1.jpg" );
insert into Producto_imagenes values(2, "C:\Users\tavog\imagen2.jpg" );
insert into Producto_imagenes values(3, "C:\Users\tavog\imagen3.jpg" );

--Se insertan algunos comentarios como prueba
insert into Comentario values(1, 4, "2021-10-12", "Muy buen producto", null, 1, "98765554" );
insert into Comentario values(2, 5, "2021-10-11", "Excelente producto", null, 2, "3212345" );

--Se crean algunos administradores como prueba en la base de datos
insert into admin values ("123","pedro@email.com","Pedro Sanchez","pedro123");
insert into admin values ("456","maria@email.com","Maria Elena","maria123");
insert into admin values ("789","david@email.com","David Lopez","david123");

--Se crea algunos chats como prueba en la base de datos
insert into chat values (1,"100765489",1);
insert into chat values (2,"42785998",2);
insert into chat values (3,"13838543",3);

--Se crea algunos mensajes como prueba para el chat en la base de datos
insert into mensaje values (1,"Juan","2021/10/06","Hola, esta disponible este producto?",1);
insert into mensaje values (2,"Juan","2021/10/06","Me interesa el producto",2);
insert into mensaje values (3,"Juan","2021/10/06","Espero su respuesta muchas gracias.",3);

--Se crean compras como prueba en la base de datos
insert into compra values (1,"2021/10/06","Efectivo","100765489");
insert into compra values (2,"2021/10/06","Efectivo","42785998");
insert into compra values (3,"2021/10/06","Efectivo","13838543");

--Se crean detalles de compras como prueba en la base de datos
insert into detalle_compra values (1,200000.00,2,1,1);
insert into detalle_compra values (2,500000.00,5,2,2);
insert into detalle_compra values (3,800000.00,1,3,3);

--Se crean favoritos como prueba en la base de datos
insert into producto_usuarios_favoritos values(1,"100765489");
insert into producto_usuarios_favoritos values(2,"100765489");
insert into producto_usuarios_favoritos values(3,"100765489");

--Se crean subastas como prueba en la base de datos
insert into subasta values(1, "2022/10/19", 1);
insert into subasta values(2, "2022/10/19", 2);
insert into subasta values(3, "2022/10/19", 3);

--Se crean subastas_usuario como prueba en la base de datos
insert into subasta_usuario values(1, "2021/10/19", 1000.0, 1, "100765489");
insert into subasta_usuario values(2, "2021/10/19", 2000.0, 2, "42785998");
insert into subasta_usuario values(3, "2021/10/19", 3000.0, 3, "42785998");