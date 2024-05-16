
DROP TABLE `bossa_box`.`tools`;

CREATE TABLE `bossa_box`.`tools` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `link` VARCHAR(255) NOT NULL,
  `tags` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `bossa_box`.`tools` (`title`, `link`, `tags`, `description`) VALUES ('fastify', 'https://www.fastify.io/', 'web,framework,node,http2,https,localhost', 'Extremely fast and simple, low-overhead web framework for NodeJS. Supports HTTP2.');
INSERT INTO `bossa_box`.`tools` (`title`, `link`, `tags`, `description`) VALUES ('express', 'https://expressjs.com/', 'web,framework,node,http,https,localhost', 'Fast, unopinionated, minimalist web framework for NodeJS. Supports HTTP.');
INSERT INTO `bossa_box`.`tools` (`title`, `link`, `tags`, `description`) VALUES ('koa', 'https://koajs.com/', 'web,framework,node,http,https,localhost', 'Next generation web framework for NodeJS, created by the same team that created Express. Supports HTTP.');