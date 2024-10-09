INSERT INTO users (id, walletid, positionid, username) VALUES (1,1,1, 'aerith');
INSERT INTO wallets (id, walletname, currentbalance, transactionid, walletid) VALUES (  1, 'zackswallet',100.0, 1 , 1);
INSERT INTO transactions (id, amount, type, isreverted, dateoftransaction, transactionid) VALUES ( 1, 1.0, 1, false, '2024-01-01', 1);
INSERT INTO positions (id, sizeofposition, typeofposition, isexecuted, leverage, marketid, positionid) VALUES ( 1, 10.0, 1, false, 10, 1, 1 );
INSERT INTO positions (id, sizeofposition, typeofposition, isexecuted, leverage, marketid, positionid) VALUES ( 2, 10.0, 1, false, 10, 2, 1 );
INSERT INTO market (id, marketname, currentmarketprice, entrymarketprice, marketid) values ( 1, 'WALLMARKET', 10.0, 10.0, 1 );
INSERT INTO market (id, marketname, currentmarketprice, entrymarketprice, marketid) values ( 2, 'FISHERMANHORIZON', 20.0, 25.0, 2 );