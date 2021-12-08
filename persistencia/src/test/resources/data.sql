--Se crean los departamentos en la base de datos
insert into departamento values(1, "Quindio");
insert into departamento values(2, "Risaralda");
insert into departamento values(3, "Antioquia");
insert into departamento values(4, "Valle del cauca");
insert into departamento values(5, "Cundinamarca");

--Se crean las ciudades en la base de datos
insert into Ciudad values(1, "Armenia", 1);
insert into Ciudad values(2, "Pereira", 2);
insert into Ciudad values(3, "Medellin", 3);
insert into Ciudad values(4, "Bogota", 4);
insert into Ciudad values(5, "Cali", 5);

--Se crean las categorias en la base de datos como datos de prueba
insert into Categoria values(1, "Ropa");
insert into Categoria values(2, "Tenis");
insert into Categoria values(3, "Jeans");
insert into Categoria values(4, "Carros");
insert into Categoria values(5, "Alcohol");

--Se crean algunos usuarios en la base de datos
insert into usuario values("100765489", "carlos@gmail.com", "Carlos Florez", "123", 1);
insert into usuario values("42785998", "maria@gmail.com", "Maria Zapata", "1130", 1);
insert into usuario values("13838543", "antonio@gmail.com", "Antonio Lopez", "1232", 3);
insert into usuario values("98765554", "gisela@gmail.com", "Gisela Carvajal", "0000", 5);
insert into usuario values("3212345", "mariana@gmail.com", "Mariana Carabali", "111", 5);

--Se asignan algunos telefonos a usuarios en la base de datos
insert into usuario_telefonos values("100765489", "3225758645");
insert into usuario_telefonos values("100765489", "3225745454");
insert into usuario_telefonos values("42785998", "322515144");
insert into usuario_telefonos values("42785998", "351414151");
insert into usuario_telefonos values("42785998", "35484848");

--Se insertan algunos domicilios en la base de datos
insert into domicilio values (1, "Zuldemayda", "13", "", "15", "20", 1, "100765489");
insert into domicilio values (2, "Genesis", "15", "", "20", "8", 1, "42785998");
insert into domicilio values (3, "Naranjos", "8", "", "3", "20", 2, "42785998");

--Se insertan algunos productos en la base de datos
insert into Producto values(1, "Camisa de tela fria", null, "2021-11-11", "esqueleto", 20000.0, 0, 5, 1, "100765489");
insert into Producto values(2, "Jean jogger", null, "2021-10-22", "Jogger", 30000.0, 0, 15, 2, "42785998"  );
insert into Producto values(3, "Tenis af1 blancos", null, "2021-12-22", "AF1", 130000.0, 0, 3, 3, "13838543"  );
insert into Producto values(4, "Esqueleto de tela caliente", null, "2021-11-12", "Camisa", 20000.0, 0, 5, 1, "100765489"  );

--Se insertan algunos productos y categoria en la tabla producto_categorias
insert into producto_categorias values(1, 1 );
insert into producto_categorias values(2, 1 );
insert into producto_categorias values(3, 1 );
insert into producto_categorias values(4, 2 );


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
insert into compra values (1,"2021/10/06","EFECTIVO", 5000, 1, "100765489");
insert into compra values (2,"2021/10/06","EFECTIVO", 5000, 2, "42785998");
insert into compra values (3,"2021/10/06","EFECTIVO", 5000, 3, "13838543");
insert into compra values (4,"2021/10/06","EFECTIVO", 5000, 1, "100765489");
insert into compra values (5,"2021/10/06","EFECTIVO", 5000, 1, "100765489");
insert into compra values (6,"2021/10/06","EFECTIVO", 5000, 1, "100765489");

--Se crean detalles de compras como prueba en la base de datos
insert into detalle_compra values (1,200000.00,2,1,1);
insert into detalle_compra values (2,500000.00,5,2,2);
insert into detalle_compra values (3,800000.00,1,3,3);

--Se crean favoritos como prueba en la base de datos
insert into producto_usuario values(1,"100765489");
insert into producto_usuario values(2,"100765489");
insert into producto_usuario values(3,"100765489");

--Se crean subastas como prueba en la base de datos
insert into subasta values(1, "2022/10/19", 1);
insert into subasta values(2, "2022/10/19", 2);
insert into subasta values(3, "2022/10/19", 3);

--Se crean subastas_usuario como prueba en la base de datos
insert into subasta_usuario values(1, "2021/10/19", 1000.0, 1, "100765489");
insert into subasta_usuario values(2, "2021/10/19", 2000.0, 2, "42785998");
insert into subasta_usuario values(3, "2021/10/19", 3000.0, 3, "42785998");

--Se crean favores como prueba en la base de datos
insert into favor values(1, "Comprar mercado", "Entregar en puerta", 1, 2, "100765489");
insert into favor values(2, "Recoger tv", "llevarlo a reparar", 2, 3, "42785998");
insert into favor values(3, "recoger plata" , "pagar recibo", 3, 1, "42785998");