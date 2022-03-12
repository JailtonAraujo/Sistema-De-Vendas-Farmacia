CREATE DATABASE  IF NOT EXISTS `bd_sisfarmacia` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bd_sisfarmacia`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bd_sisfarmacia
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(120) COLLATE utf8_bin NOT NULL,
  `cpf` bigint NOT NULL,
  `idcontato` int DEFAULT NULL,
  `idendereco` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_cliente_contato_idx` (`idcontato`),
  KEY `fk_cliente_endereco_idx` (`idendereco`),
  CONSTRAINT `fk_cliente_contato` FOREIGN KEY (`idcontato`) REFERENCES `contato` (`ID`),
  CONSTRAINT `fk_cliente_endereco` FOREIGN KEY (`idendereco`) REFERENCES `endereco` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'carlos',54353453453,3,2),(2,'Arlindo',55455454546,4,3),(3,'Josa',31231231313,5,4),(4,'Hugo Camargo',21312312321,6,5),(5,'Gustavo Andrade',21321321313,7,6),(6,'Francisco Carlos',23423423422,8,7),(7,'Vinicius',23432423424,9,8),(8,'Paulo',23423423423,10,9),(9,'Ricardo Nunes',12312321311,11,10),(10,'Daniel Avelar',43242342342,12,11),(11,'Jose Abreu',23423423423,13,12),(12,'Bruno Abreu',42342342342,14,13);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contato`
--

DROP TABLE IF EXISTS `contato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contato` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `email` varchar(60) DEFAULT NULL,
  `telefone` bigint DEFAULT NULL,
  `Pessoa_fk` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `pessoa_idx` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contato`
--

LOCK TABLES `contato` WRITE;
/*!40000 ALTER TABLE `contato` DISABLE KEYS */;
INSERT INTO `contato` VALUES (2,'jailtonsd@jhgfd.com',54546546546,NULL),(3,'33455555',45334534343,NULL),(4,'3434344234234',32323232344,NULL),(5,'443334',43242342343,NULL),(6,'2232',12312312312,NULL),(7,'333223',12321312312,NULL),(8,'444432',24342342344,NULL),(9,'23444',23423423423,NULL),(10,'234234234',33434324233,NULL),(11,'3212312',12312312321,NULL),(12,'3434',23423423423,NULL),(13,'4443242ff',23423423424,NULL),(14,'343244234',23423423423,NULL);
/*!40000 ALTER TABLE `contato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereco` (
  `id` int NOT NULL AUTO_INCREMENT,
  `logradouro` varchar(60) NOT NULL,
  `cidade` varchar(60) NOT NULL,
  `numero` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,'jjajjs','ddssss',0),(2,'4555','45454',44),(3,'ddf','dfd',32),(4,'fsd','sdfsd',22),(5,'12321','12312',33),(6,'332','123',33),(7,'4234','234234',42342),(8,'234234','34234',44),(9,'34234','234',234),(10,'323','21321',33),(11,'234','234',34),(12,'34234','234',33),(13,'3434','23423',442);
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itempedido`
--

DROP TABLE IF EXISTS `itempedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itempedido` (
  `idItemPedido` int NOT NULL AUTO_INCREMENT,
  `idPedido` int NOT NULL,
  `idProduto` int NOT NULL,
  `quantidade` int NOT NULL,
  PRIMARY KEY (`idItemPedido`),
  KEY `fk_itemPedido_produto_idx` (`idProduto`),
  KEY `fk_itemPedido_pedido` (`idPedido`),
  KEY `fk_itemPedido_produto_idx1` (`idProduto`),
  CONSTRAINT `fk_itemPedido_pedido` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`idPedido`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_itemPedido_produto` FOREIGN KEY (`idProduto`) REFERENCES `produto` (`idtabela`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itempedido`
--

LOCK TABLES `itempedido` WRITE;
/*!40000 ALTER TABLE `itempedido` DISABLE KEYS */;
INSERT INTO `itempedido` VALUES (1,7,5,2),(2,7,3,2),(3,7,6,2),(4,7,8,2),(5,7,5,2),(6,7,6,2),(7,8,6,2),(8,8,4,2),(9,8,8,2),(10,9,6,2),(11,9,3,2),(12,10,7,2),(13,10,4,2),(14,10,3,2),(15,11,4,2),(16,11,6,2),(17,12,4,2),(18,12,6,3),(19,12,7,3),(20,12,8,1),(21,12,3,1),(22,13,4,2),(23,13,5,2),(24,13,3,2),(25,14,4,3),(26,14,5,3),(27,14,8,3),(28,15,3,3),(29,15,8,3),(30,15,5,3),(31,16,6,2),(33,18,6,2),(34,18,4,3),(35,19,5,2),(36,19,7,2),(37,20,8,5),(38,20,4,6),(39,20,2,2),(40,20,2,3),(41,20,6,2),(42,20,2,1),(43,21,1,1),(44,24,2,5),(45,24,4,3),(46,25,3,2),(47,27,2,2),(48,28,3,2),(49,28,4,8),(50,28,5,2);
/*!40000 ALTER TABLE `itempedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `idPedido` int NOT NULL AUTO_INCREMENT,
  `dataPedido` date NOT NULL,
  `precoTotal` decimal(5,2) NOT NULL,
  `quantidadeTotal` int NOT NULL,
  `pagamento` int NOT NULL,
  `idCliente` int DEFAULT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`idPedido`),
  KEY `fk_pedido_cliente` (`idCliente`),
  KEY `fk_pedido_usuario` (`idUsuario`),
  CONSTRAINT `fk_pedido_cliente` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`ID`),
  CONSTRAINT `fk_pedido_usuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (7,'2022-03-01',60.60,12,2,2,1),(8,'2022-03-01',31.20,6,1,1,1),(9,'2022-03-01',19.60,4,1,1,1),(10,'2022-03-01',12.60,6,1,1,1),(11,'2022-03-01',22.20,4,1,1,1),(12,'2022-03-01',46.80,11,2,9,1),(13,'2022-03-01',14.80,6,2,4,1),(14,'2022-03-02',32.10,9,1,5,1),(15,'2022-03-02',28.20,9,2,10,1),(16,'2022-03-02',17.20,2,1,1,1),(18,'2022-03-03',24.70,5,2,3,1),(19,'2022-03-03',12.60,4,2,6,1),(20,'2022-03-03',69.70,19,2,4,1),(21,'2022-03-03',8.00,1,1,1,1),(23,'2022-03-03',20.00,5,1,NULL,1),(24,'2022-03-03',20.00,8,2,12,1),(25,'2022-03-03',2.40,2,2,6,1),(27,'2022-03-03',5.00,2,1,NULL,1),(28,'2022-03-10',29.80,12,2,7,2);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `idtabela` int NOT NULL AUTO_INCREMENT,
  `idproduto` bigint NOT NULL,
  `nome` varchar(60) NOT NULL,
  `descricao` varchar(60) NOT NULL,
  `estoque` int NOT NULL,
  `preco` decimal(5,2) NOT NULL,
  PRIMARY KEY (`idtabela`,`idproduto`),
  UNIQUE KEY `Produt_UN` (`idproduto`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,7898955762066,'Ovos avimil','bandeija com 12 ovos',19,8.00),(2,4654564,'Macarrão','macarrão galo',12,2.50),(3,546546545,'Sal','Sal de cozinha ',20,1.20),(4,65654654465,'Arroz','Arroz querubim',24,2.50),(5,465465465,'Suco de caixa','Suco de caixa 1L',5,3.70),(6,564654654,'Dipirona','50ml',12,8.60),(7,555654564,'Tylenol','80ml',38,2.60),(8,554565212,'Doril','Cartela com 10',35,4.50);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `idcontato` int DEFAULT NULL,
  `idendereco` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_usuario_contato_idx` (`idcontato`),
  KEY `fk_usuario_endereco_idx` (`idendereco`),
  CONSTRAINT `fk_usuario_contato` FOREIGN KEY (`idcontato`) REFERENCES `contato` (`ID`),
  CONSTRAINT `fk_usuario_endereco` FOREIGN KEY (`idendereco`) REFERENCES `endereco` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','admin','admin',NULL,NULL),(2,'jailton de Araujo Santos','123','jailton',NULL,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-12 17:49:49
