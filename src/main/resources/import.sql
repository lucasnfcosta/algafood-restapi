insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('McLucas', 0.54, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Stargabs', 1.56, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('JiafeiShits', 1.89, 2);

insert into estado (nome) values ('Minas Gerais');
insert into cidade (nome, estado_id) values ("Belo Horizonte", 1);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into forma_pagamento_restaurante (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);