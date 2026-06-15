-- ======================================================
-- 🚀 GALPAX_26: MASTER INSTALL SCRIPT
-- Versão: 1.1 (15/06/2026)
-- Descrição: Cria o banco do zero, tabelas e popula dados.
-- Ajustes: Corrigido ENUM de status e completadas mensalidades.
-- ======================================================

-- 1. RESET DO AMBIENTE
DROP DATABASE IF EXISTS galpax;
CREATE DATABASE galpax;
USE galpax;

-- 2. TABELA: CAD_ADMIN (Administradores do Sistema)
CREATE TABLE `cad_admin` (
  `id_admin` int NOT NULL AUTO_INCREMENT,
  `nome_admin` varchar(100) NOT NULL,
  `cpf_admin` char(11) NOT NULL,
  `email_admin` varchar(100) NOT NULL,
  `senha_admin` varchar(255) NOT NULL,
  PRIMARY KEY (`id_admin`),
  UNIQUE KEY `cpf_admin` (`cpf_admin`),
  UNIQUE KEY `email_admin` (`email_admin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `cad_admin` VALUES 
(1,'João Silva','12345678901','joao.admin@email.com','Admin@123'),
(2,'Maria Oliveira','98765432100','maria.admin@email.com','Admin@456');

-- 3. TABELA: CAD_LOJA (Clientes/Lojas nos Galpões)
CREATE TABLE `cad_loja` (
  `id_loja` int NOT NULL AUTO_INCREMENT,
  `nome_loja` varchar(255) NOT NULL,
  `cnpj_loja` varchar(18) NOT NULL,
  `responsavel_loja` varchar(255) NOT NULL,
  `telefone_loja` varchar(20) NOT NULL,
  `email_loja` varchar(255) NOT NULL,
  `endereco_loja` varchar(255) NOT NULL,
  `sala_loja` varchar(50) NOT NULL,
  `tipo_loja` varchar(100) NOT NULL,
  `aluguel_loja` decimal(10,2) NOT NULL,
  `status_loja` varchar(50) NOT NULL,
  `nivel_loja` varchar(50) NOT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`id_loja`),
  UNIQUE KEY `cnpj_loja` (`cnpj_loja`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO cad_loja (nome_loja, cnpj_loja, responsavel_loja, telefone_loja, email_loja, endereco_loja, sala_loja, tipo_loja, aluguel_loja, status_loja, nivel_loja, senha) VALUES
('Tech Solution','10.123.456/0001-01','Carlos Souza','85988880001','loja01@galpax.com','Galpão A','101','Tecnologia',2500.00,'Ativo','cliente','123456'),
('Padaria Pão Quente','10.123.456/0001-02','Ana Lima','85988880002','loja02@galpax.com','Galpão A','102','Alimentação',1800.00,'Ativo','cliente','123456'),
('Auto Peças Silva','10.123.456/0001-03','Joao Silva','85988880003','loja03@galpax.com','Galpão A','103','Automotivo',3200.00,'Ativo','cliente','123456'),
('Moda Fashion','10.123.456/0001-04','Marta Rocha','85988880004','loja04@galpax.com','Galpão A','104','Vestuário',2100.00,'Ativo','cliente','123456'),
('Pet Shop Amigão','10.123.456/0001-05','Pedro Paulo','85988880005','loja05@galpax.com','Galpão A','105','Serviços',1950.00,'Ativo','cliente','123456'),
('Móveis Design','10.123.456/0001-06','Lucia Costa','85988880006','loja06@galpax.com','Galpão A','106','Móveis',4500.00,'Ativo','cliente','123456'),
('Farmácia Saúde','10.123.456/0001-07','Roberto Dias','85988880007','loja07@galpax.com','Galpão A','107','Saúde',2800.00,'Ativo','cliente','123456'),
('Livraria Saber','10.123.456/0001-08','Fernanda Montenegro','85988880008','loja08@galpax.com','Galpão A','108','Cultura',1600.00,'Ativo','cliente','123456'),
('Academia Fit','10.123.456/0001-09','Marcos Lima','85988880009','loja09@galpax.com','Galpão A','109','Esporte',5000.00,'Ativo','cliente','123456'),
('Ótica Visão','10.123.456/0001-10','Clara Nunes','85988880010','loja10@galpax.com','Galpão A','110','Saúde',2200.00,'Ativo','cliente','123456'),
('Info Center','10.123.456/0001-11','Daniel Alves','85988880011','loja11@galpax.com','Galpão B','111','Tecnologia',2700.00,'Ativo','cliente','123456'),
('Sacolão do Povo','10.123.456/0001-12','Sebastião Rosa','85988880012','loja12@galpax.com','Galpão B','112','Alimentação',1400.00,'Ativo','cliente','123456'),
('Relojoaria TicTac','10.123.456/0001-13','Juliana Paes','85988880013','loja13@galpax.com','Galpão B','113','Acessórios',3100.00,'Ativo','cliente','123456'),
('Casa das Tintas','10.123.456/0001-14','Hugo Souza','85988880014','loja14@galpax.com','Galpão B','114','Construção',3800.00,'Ativo','cliente','123456'),
('Brinquedos Mania','10.123.456/0001-15','Aline Moraes','85988880015','loja15@galpax.com','Galpão B','115','Infantil',2000.00,'Ativo','cliente','123456'),
('Restaurante Tempero','10.123.456/0001-16','Felipe Neto','85988880016','loja16@galpax.com','Galpão B','116','Alimentação',4200.00,'Ativo','cliente','123456'),
('Floricultura Beija-Flor','10.123.456/0001-17','Gisele B.','85988880017','loja17@galpax.com','Galpão B','117','Decoração',1500.00,'Ativo','cliente','123456'),
('Sapataria Conforto','10.123.456/0001-18','Bruno Gagliasso','85988880018','loja18@galpax.com','Galpão B','118','Vestuário',2400.00,'Ativo','cliente','123456'),
('Eletro Volt','10.123.456/0001-19','Ivete S.','85988880019','loja19@galpax.com','Galpão B','119','Elétrica',3500.00,'Ativo','cliente','123456'),
('Sorveteria Gelada','10.123.456/0001-20','Caetano V.','85988880020','loja20@galpax.com','Galpão B','120','Alimentação',1700.00,'Ativo','cliente','123456'),
('Oficina do Som','10.123.456/0001-21','Gilberto Gil','85988880021','loja21@galpax.com','Galpão C','121','Automotivo',2900.00,'Ativo','cliente','123456'),
('Chaveiro 24h','10.123.456/0001-22','Djavan O.','85988880022','loja22@galpax.com','Galpão C','122','Serviços',1200.00,'Ativo','cliente','123456'),
('Papelaria Grafite','10.123.456/0001-23','Maria Bethania','85988880023','loja23@galpax.com','Galpão C','123','Educação',1900.00,'Ativo','cliente','123456'),
('Loja do 1 Real','10.123.456/0001-24','Gal Costa','85988880024','loja24@galpax.com','Galpão C','124','Variedades',2300.00,'Ativo','cliente','123456'),
('Adega Premium','10.123.456/0001-25','Chico Buarque','85988880025','loja25@galpax.com','Galpão C','125','Bebidas',4800.00,'Ativo','cliente','123456'),
('Barbearia Estilo','10.123.456/0001-26','Jorge Ben','85988880026','loja26@galpax.com','Galpão C','126','Estética',1850.00,'Ativo','cliente','123456'),
('Consultório Dental','10.123.456/0001-27','Ney Matogrosso','85988880027','loja27@galpax.com','Galpão C','127','Saúde',5500.00,'Ativo','cliente','123456'),
('Loja de Games','10.123.456/0001-28','Zeca Pagodinho','85988880028','loja28@galpax.com','Galpão C','128','Tecnologia',3300.00,'Ativo','cliente','123456'),
('Artigos de Pesca','10.123.456/0001-29','Alcione','85988880029','loja29@galpax.com','Galpão C','129','Lazer',2050.00,'Ativo','cliente','123456'),
('Assistência Celular','10.123.456/0001-30','Thiaguinho','85988880030','loja30@galpax.com','Galpão C','130','Tecnologia',2600.00,'Ativo','cliente','123456'),
('Distribuidora de Água','10.123.456/0001-31','Ludmilla','85988880031','loja31@galpax.com','Galpão D','131','Serviços',1350.00,'Ativo','cliente','123456'),
('Mercadinho São José','10.123.456/0001-32','Pabllo Vittar','85988880032','loja32@galpax.com','Galpão D','132','Alimentação',3900.00,'Ativo','cliente','123456');

-- 4. TABELA: CAD_CARRO (Veículos Cadastrados)
CREATE TABLE `cad_carro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `placa_carro` varchar(10) NOT NULL,
  `modelo_carro` varchar(50) NOT NULL,
  `cnh_carro` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO cad_carro (placa_carro, modelo_carro, cnh_carro) VALUES
('ABC1234','Gol','11111111111'), ('DEF5678','Uno','22222222222'), ('GHI9012','Palio','33333333333'),
('JKL3456','Corolla','44444444444'), ('MNO7890','Civic','55555555555'), ('PQR1234','HB20','66666666666'),
('STU5678','Onix','77777777777'), ('VWX9012','Hilux','88888888888'), ('YZA3456','HR-V','99999999999'),
('BCD7890','Renegade','10101010101'), ('EFG1234','Toro','12121212121'), ('HIJ5678','Mobi','13131313131'),
('KLM9012','Argo','14141414141'), ('NOP3456','Kwid','15151515151'), ('QRS7890','Compass','16161616161'),
('TUV1234','Creta','17171717171'), ('WXY5678','T-Cross','18181818181'), ('ZAB9012','Nivus','19191919191'),
('CDE3456','Polo','20202020202'), ('FGH7890','Virtus','21212121212');

-- 5. TABELA: MENSALIDADE (Financeiro)
-- AJUSTE: O status 'Pago' foi adicionado para coincidir com a lógica do código Java (Bancodedados.java -> pagarMensalidade)
CREATE TABLE `mensalidade` (
  `id_mensalidade` int NOT NULL AUTO_INCREMENT,
  `id_loja` int NOT NULL,
  `mensalidade` decimal(10,2) NOT NULL,
  `vencimento` date NOT NULL,
  `data_pagamento` date DEFAULT NULL,
  `status` enum('Pendente','Pago','Atrasada','Cancelada') DEFAULT 'Pendente',
  PRIMARY KEY (`id_mensalidade`),
  KEY `fk_mensalidade_loja` (`id_loja`),
  CONSTRAINT `fk_mensalidade_loja` FOREIGN KEY (`id_loja`) REFERENCES `cad_loja` (`id_loja`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- MENSALIDADES COMPLETAS (Pelo menos uma para cada uma das 32 lojas)
INSERT INTO mensalidade (id_loja, mensalidade, vencimento, status, data_pagamento) VALUES
-- Maio (Mês Passado - Pagas)
(1, 2500.00, '2026-05-10', 'Pago', '2026-05-09'),
(2, 1800.00, '2026-05-10', 'Pago', '2026-05-10'),
(3, 3200.00, '2026-05-10', 'Pago', '2026-05-05'),
(4, 2100.00, '2026-05-10', 'Pago', '2026-05-12'),
(5, 1950.00, '2026-05-10', 'Pago', '2026-05-10'),

-- Junho (Mês Atual - Pendentes/Pagas/Atrasadas)
(1, 2500.00, '2026-06-10', 'Pendente', NULL),
(2, 1800.00, '2026-06-10', 'Pendente', NULL),
(3, 3200.00, '2026-06-10', 'Pendente', NULL),
(4, 2100.00, '2026-06-10', 'Pendente', NULL),
(5, 1950.00, '2026-06-10', 'Pago', '2026-06-10'),
(6, 4500.00, '2026-06-10', 'Pendente', NULL),
(7, 2800.00, '2026-06-10', 'Pago', '2026-06-08'),
(8, 1600.00, '2026-06-10', 'Pendente', NULL),
(9, 5000.00, '2026-06-10', 'Atrasada', NULL),
(10, 2200.00, '2026-06-10', 'Pendente', NULL),
(11, 2700.00, '2026-06-10', 'Pendente', NULL),
(12, 1400.00, '2026-06-10', 'Pendente', NULL),
(13, 3100.00, '2026-06-10', 'Pendente', NULL),
(14, 3800.00, '2026-06-10', 'Pendente', NULL),
(15, 2000.00, '2026-06-10', 'Pendente', NULL),
(16, 4200.00, '2026-06-10', 'Pendente', NULL),
(17, 1500.00, '2026-06-10', 'Pago', '2026-06-05'),
(18, 2400.00, '2026-06-10', 'Pendente', NULL),
(19, 3500.00, '2026-06-10', 'Pendente', NULL),
(20, 1700.00, '2026-06-10', 'Pendente', NULL),
(21, 2900.00, '2026-06-10', 'Pendente', NULL),
(22, 1200.00, '2026-06-10', 'Pago', '2026-06-10'),
(23, 1900.00, '2026-06-10', 'Pendente', NULL),
(24, 2300.00, '2026-06-10', 'Pendente', NULL),
(25, 4800.00, '2026-06-10', 'Atrasada', NULL),
(26, 1850.00, '2026-06-10', 'Pendente', NULL),
(27, 5500.00, '2026-06-10', 'Pendente', NULL),
(28, 3300.00, '2026-06-10', 'Pendente', NULL),
(29, 2050.00, '2026-06-10', 'Pendente', NULL),
(30, 2600.00, '2026-06-10', 'Pendente', NULL),
(31, 1350.00, '2026-06-10', 'Pendente', NULL),
(32, 3900.00, '2026-06-10', 'Pendente', NULL);
