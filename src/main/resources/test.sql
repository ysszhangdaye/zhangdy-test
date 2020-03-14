-- 暂停交易
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_DEAL','BTC_USDT_COINBASE_ENCRY',41,182,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_DEAL','ETH_USDT_COINBASE_ENCRY',41,182,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_DEAL','ETC_USDT_COINBASE_ENCRY',41,182,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_DEAL','ETH_BTC_COINBASE_ENCRY',41,182,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_DEAL','ETC_BTC_COINBASE_ENCRY',41,182,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_DEAL','ETC_ETH_COINBASE_ENCRY',41,182,false,0);

-- 暂停买入
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_BUY','BTC_USDT_COINBASE_ENCRY',41,148,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_BUY','ETH_USDT_COINBASE_ENCRY',41,148,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_BUY','ETC_USDT_COINBASE_ENCRY',41,148,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_BUY','ETH_BTC_COINBASE_ENCRY',41,148,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_BUY','ETC_BTC_COINBASE_ENCRY',41,148,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_BUY','ETC_ETH_COINBASE_ENCRY',41,148,false,0)
-- 暂停卖出
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_SELL','BTC_USDT_COINBASE_ENCRY',41,149,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_SELL','ETH_USDT_COINBASE_ENCRY',41,149,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_SELL','ETC_USDT_COINBASE_ENCRY',41,149,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_SELL','ETH_BTC_COINBASE_ENCRY',41,149,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_SELL','ETC_BTC_COINBASE_ENCRY',41,149,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_STOP_SELL','ETC_ETH_COINBASE_ENCRY',41,149,false,0);
-- 订单价格小数位
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_PRICE_DECIMAL','BTC_USDT_COINBASE_ENCRY',41,150,2,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_PRICE_DECIMAL','ETH_USDT_COINBASE_ENCRY',41,150,2,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_PRICE_DECIMAL','ETC_USDT_COINBASE_ENCRY',41,150,4,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_PRICE_DECIMAL','ETH_BTC_COINBASE_ENCRY',41,150,6,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_PRICE_DECIMAL','ETC_BTC_COINBASE_ENCRY',41,150,6,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_PRICE_DECIMAL','ETC_ETH_COINBASE_ENCRY',41,150,6,0);
-- 订单数量小数位
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_VOL_DECIMAL','BTC_USDT_COINBASE_ENCRY',41,151,6,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_VOL_DECIMAL','ETH_USDT_COINBASE_ENCRY',41,151,4,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_VOL_DECIMAL','ETC_USDT_COINBASE_ENCRY',41,151,4,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_VOL_DECIMAL','ETH_BTC_COINBASE_ENCRY',41,151,4,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_VOL_DECIMAL','ETC_BTC_COINBASE_ENCRY',41,151,4,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_VOL_DECIMAL','ETC_ETH_COINBASE_ENCRY',41,151,4,0);
-- 订单额小数位
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_AMOUNT_DECIMAL','BTC_USDT_COINBASE_ENCRY',41,152,8,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_AMOUNT_DECIMAL','ETH_USDT_COINBASE_ENCRY',41,152,8,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_AMOUNT_DECIMAL','ETC_USDT_COINBASE_ENCRY',41,152,8,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_AMOUNT_DECIMAL','ETH_BTC_COINBASE_ENCRY',41,152,4,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_AMOUNT_DECIMAL','ETC_BTC_COINBASE_ENCRY',41,152,4,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_AMOUNT_DECIMAL','ETC_ETH_COINBASE_ENCRY',41,152,4,0);
-- 单次最低卖出委托价格(倍)
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_LOW_SELL_PRICE','BTC_USDT_COINBASE_ENCRY',41,153,0.01,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_LOW_SELL_PRICE','ETH_USDT_COINBASE_ENCRY',41,153,0.01,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_LOW_SELL_PRICE','ETC_USDT_COINBASE_ENCRY',41,153,0.0001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_LOW_SELL_PRICE','ETH_BTC_COINBASE_ENCRY',41,153,0.000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_LOW_SELL_PRICE','ETC_BTC_COINBASE_ENCRY',41,153,0.000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_LOW_SELL_PRICE','ETC_ETH_COINBASE_ENCRY',41,153,0.000001,0);
-- 单次最高卖出委托价格(倍)
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_HIGHT_SELL_PRICE','BTC_USDT_COINBASE_ENCRY',41,154,99999,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_HIGHT_SELL_PRICE','ETH_USDT_COINBASE_ENCRY',41,154,99999,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_HIGHT_SELL_PRICE','ETC_USDT_COINBASE_ENCRY',41,154,99999,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_HIGHT_SELL_PRICE','ETH_BTC_COINBASE_ENCRY',41,154,99999,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_HIGHT_SELL_PRICE','ETC_BTC_COINBASE_ENCRY',41,154,99999,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_HIGHT_SELL_PRICE','ETC_ETH_COINBASE_ENCRY',41,154,99999,0);
-- 单次最低买入委托价格(倍)
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_LOW_BUY_PRICE','BTC_USDT_COINBASE_ENCRY',41,155,0.01,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_LOW_BUY_PRICE','ETH_USDT_COINBASE_ENCRY',41,155,0.01,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_LOW_BUY_PRICE','ETC_USDT_COINBASE_ENCRY',41,155,0.0001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_LOW_BUY_PRICE','ETH_BTC_COINBASE_ENCRY',41,155,0.000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_LOW_BUY_PRICE','ETC_BTC_COINBASE_ENCRY',41,155,0.000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_LOW_BUY_PRICE','ETC_ETH_COINBASE_ENCRY',41,155,0.000001,0);
-- 单次最高买入委托价格(倍)
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_HIGHT_BUY_PRICE','BTC_USDT_COINBASE_ENCRY',41,156,99999,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_HIGHT_BUY_PRICE','ETH_USDT_COINBASE_ENCRY',41,156,99999,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_HIGHT_BUY_PRICE','ETC_USDT_COINBASE_ENCRY',41,156,99999,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_HIGHT_BUY_PRICE','ETH_BTC_COINBASE_ENCRY',41,156,99999,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_HIGHT_BUY_PRICE','ETC_BTC_COINBASE_ENCRY',41,156,99999,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_HIGHT_BUY_PRICE','ETC_ETH_COINBASE_ENCRY',41,156,99999,0);
-- 单次最小卖出委托量
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_SELL_VOL','BTC_USDT_COINBASE_ENCRY',41,157,0.0001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_SELL_VOL','ETH_USDT_COINBASE_ENCRY',41,157,0.0001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_SELL_VOL','ETC_USDT_COINBASE_ENCRY',41,157,0.0001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_SELL_VOL','ETH_BTC_COINBASE_ENCRY',41,157,0.0001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_SELL_VOL','ETC_BTC_COINBASE_ENCRY',41,157,0.0001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_SELL_VOL','ETC_ETH_COINBASE_ENCRY',41,157,0.0001,0);
-- 单次最大卖出委托量
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_SELL_VOL','BTC_USDT_COINBASE_ENCRY',41,158,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_SELL_VOL','ETH_USDT_COINBASE_ENCRY',41,158,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_SELL_VOL','ETC_USDT_COINBASE_ENCRY',41,158,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_SELL_VOL','ETH_BTC_COINBASE_ENCRY',41,158,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_SELL_VOL','ETC_BTC_COINBASE_ENCRY',41,158,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_SELL_VOL','ETC_ETH_COINBASE_ENCRY',41,158,1000000000,0);
-- 单次最小买入委托量
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_BUY_VOL','BTC_USDT_COINBASE_ENCRY',41,159,0.0001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_BUY_VOL','ETH_USDT_COINBASE_ENCRY',41,159,0.0001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_BUY_VOL','ETC_USDT_COINBASE_ENCRY',41,159,0.0001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_BUY_VOL','ETH_BTC_COINBASE_ENCRY',41,159,0.0001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_BUY_VOL','ETC_BTC_COINBASE_ENCRY',41,159,0.0001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_BUY_VOL','ETC_ETH_COINBASE_ENCRY',41,159,0.0001,0);
-- 单次最大买入委托量
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_BUY_VOL','BTC_USDT_COINBASE_ENCRY',41,160,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_BUY_VOL','ETH_USDT_COINBASE_ENCRY',41,160,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_BUY_VOL','ETC_USDT_COINBASE_ENCRY',41,160,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_BUY_VOL','ETH_BTC_COINBASE_ENCRY',41,160,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_BUY_VOL','ETC_BTC_COINBASE_ENCRY',41,160,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_BUY_VOL','ETC_ETH_COINBASE_ENCRY',41,160,1000000000,0);
--单次最小买入委托额
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_BUY_AMOUNT','BTC_USDT_COINBASE_ENCRY',41,161,0.000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_BUY_AMOUNT','ETH_USDT_COINBASE_ENCRY',41,161,0.000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_BUY_AMOUNT','ETC_USDT_COINBASE_ENCRY',41,161,0.00000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_BUY_AMOUNT','ETH_BTC_COINBASE_ENCRY',41,161,0.00000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_BUY_AMOUNT','ETC_BTC_COINBASE_ENCRY',41,161,0.00000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_BUY_AMOUNT','ETC_ETH_COINBASE_ENCRY',41,161,0.00000001,0);
-- 单次最大买入委托额
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_BUY_AMOUNT','BTC_USDT_COINBASE_ENCRY',41,162,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_BUY_AMOUNT','ETH_USDT_COINBASE_ENCRY',41,162,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_BUY_AMOUNT','ETC_USDT_COINBASE_ENCRY',41,162,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_BUY_AMOUNT','ETH_BTC_COINBASE_ENCRY',41,162,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_BUY_AMOUNT','ETC_BTC_COINBASE_ENCRY',41,162,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_BUY_AMOUNT','ETC_ETH_COINBASE_ENCRY',41,162,1000000000,0);
-- Taker下单买入费率
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TAKER_BUY_RATE','BTC_USDT_COINBASE_ENCRY',41,163,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TAKER_BUY_RATE','ETH_USDT_COINBASE_ENCRY',41,163,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TAKER_BUY_RATE','ETC_USDT_COINBASE_ENCRY',41,163,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TAKER_BUY_RATE','ETH_BTC_COINBASE_ENCRY',41,163,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TAKER_BUY_RATE','ETC_BTC_COINBASE_ENCRY',41,163,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TAKER_BUY_RATE','ETC_ETH_COINBASE_ENCRY',41,163,0.002,0);
-- Maker下单买入费率
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAKER_BUY_RATE','BTC_USDT_COINBASE_ENCRY',41,164,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAKER_BUY_RATE','ETH_USDT_COINBASE_ENCRY',41,164,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAKER_BUY_RATE','ETC_USDT_COINBASE_ENCRY',41,164,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAKER_BUY_RATE','ETH_BTC_COINBASE_ENCRY',41,164,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAKER_BUY_RATE','ETC_BTC_COINBASE_ENCRY',41,164,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAKER_BUY_RATE','ETC_ETH_COINBASE_ENCRY',41,164,0.002,0);
-- 单次下单买入最低手续费
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_BUY_MIN_FEE','BTC_USDT_COINBASE_ENCRY',41,165,0.0000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_BUY_MIN_FEE','ETH_USDT_COINBASE_ENCRY',41,165,0.0000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_BUY_MIN_FEE','ETC_USDT_COINBASE_ENCRY',41,165,0.0000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_BUY_MIN_FEE','ETH_BTC_COINBASE_ENCRY',41,165,0.0000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_BUY_MIN_FEE','ETC_BTC_COINBASE_ENCRY',41,165,0.0000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_BUY_MIN_FEE','ETC_ETH_COINBASE_ENCRY',41,165,0.0000001,0);
-- 单次下单买入最高手续费
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_BUY_MAX_FEE','BTC_USDT_COINBASE_ENCRY',41,166,2000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_BUY_MAX_FEE','ETH_USDT_COINBASE_ENCRY',41,166,2000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_BUY_MAX_FEE','ETC_USDT_COINBASE_ENCRY',41,166,2000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_BUY_MAX_FEE','ETH_BTC_COINBASE_ENCRY',41,166,2000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_BUY_MAX_FEE','ETC_BTC_COINBASE_ENCRY',41,166,2000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_BUY_MAX_FEE','ETC_ETH_COINBASE_ENCRY',41,166,2000000,0);
-- 单次下单卖出最低手续费
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_SELL_MIN_FEE','BTC_USDT_COINBASE_ENCRY',41,167,0.0000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_SELL_MIN_FEE','ETH_USDT_COINBASE_ENCRY',41,167,0.0000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_SELL_MIN_FEE','ETC_USDT_COINBASE_ENCRY',41,167,0.0000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_SELL_MIN_FEE','ETH_BTC_COINBASE_ENCRY',41,167,0.0000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_SELL_MIN_FEE','ETC_BTC_COINBASE_ENCRY',41,167,0.0000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_SELL_MIN_FEE','ETC_ETH_COINBASE_ENCRY',41,167,0.0000001,0);
-- 单次下单卖出最高手续费
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_SELL_MAX_FEE','BTC_USDT_COINBASE_ENCRY',41,168,10000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_SELL_MAX_FEE','ETH_USDT_COINBASE_ENCRY',41,168,10000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_SELL_MAX_FEE','ETC_USDT_COINBASE_ENCRY',41,168,10000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_SELL_MAX_FEE','ETH_BTC_COINBASE_ENCRY',41,168,10000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_SELL_MAX_FEE','ETC_BTC_COINBASE_ENCRY',41,168,10000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_SELL_MAX_FEE','ETC_ETH_COINBASE_ENCRY',41,168,10000000,0);
-- Taker下单卖出费率
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TAKER_SELL_RATE','BTC_USDT_COINBASE_ENCRY',41,169,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TAKER_SELL_RATE','ETH_USDT_COINBASE_ENCRY',41,169,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TAKER_SELL_RATE','ETC_USDT_COINBASE_ENCRY',41,169,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TAKER_SELL_RATE','ETH_BTC_COINBASE_ENCRY',41,169,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TAKER_SELL_RATE','ETC_BTC_COINBASE_ENCRY',41,169,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TAKER_SELL_RATE','ETC_ETH_COINBASE_ENCRY',41,169,0.002,0);
-- Maker下单卖出费率
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAKER_SELL_RATE','BTC_USDT_COINBASE_ENCRY',41,170,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAKER_SELL_RATE','ETH_USDT_COINBASE_ENCRY',41,170,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAKER_SELL_RATE','ETC_USDT_COINBASE_ENCRY',41,170,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAKER_SELL_RATE','ETH_BTC_COINBASE_ENCRY',41,170,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAKER_SELL_RATE','ETC_BTC_COINBASE_ENCRY',41,170,0.002,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAKER_SELL_RATE','ETC_ETH_COINBASE_ENCRY',41,170,0.002,0);
-- 开盘价
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_OPENING_PRICE','BTC_USDT_COINBASE_ENCRY',41,171,8750.6,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_OPENING_PRICE','ETH_USDT_COINBASE_ENCRY',41,171,224.49,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_OPENING_PRICE','ETC_USDT_COINBASE_ENCRY',41,171,8.303,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_OPENING_PRICE','ETH_BTC_COINBASE_ENCRY',41,171,0.02565,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_OPENING_PRICE','ETC_BTC_COINBASE_ENCRY',41,171,0.000949,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_OPENING_PRICE','ETC_ETH_COINBASE_ENCRY',41,171,0.03698,0);
-- 开启画线
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_DRAW_LINE_ENABLE','BTC_USDT_COINBASE_ENCRY',41,172,true,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_DRAW_LINE_ENABLE','ETH_USDT_COINBASE_ENCRY',41,172,true,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_DRAW_LINE_ENABLE','ETC_USDT_COINBASE_ENCRY',41,172,true,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_DRAW_LINE_ENABLE','ETH_BTC_COINBASE_ENCRY',41,172,true,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_DRAW_LINE_ENABLE','ETC_BTC_COINBASE_ENCRY',41,172,true,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_DRAW_LINE_ENABLE','ETC_ETH_COINBASE_ENCRY',41,172,true,0);
-- 开启做市
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MARKET_MAKER_ENABLE','BTC_USDT_COINBASE_ENCRY',41,173,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MARKET_MAKER_ENABLE','ETH_USDT_COINBASE_ENCRY',41,173,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MARKET_MAKER_ENABLE','ETC_USDT_COINBASE_ENCRY',41,173,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MARKET_MAKER_ENABLE','ETH_BTC_COINBASE_ENCRY',41,173,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MARKET_MAKER_ENABLE','ETC_BTC_COINBASE_ENCRY',41,173,false,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MARKET_MAKER_ENABLE','ETC_ETH_COINBASE_ENCRY',41,173,false,0);
-- 开启撮合
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MARKET_MATCH_ENABLE','BTC_USDT_COINBASE_ENCRY',41,174,true,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MARKET_MATCH_ENABLE','ETH_USDT_COINBASE_ENCRY',41,174,true,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MARKET_MATCH_ENABLE','ETC_USDT_COINBASE_ENCRY',41,174,true,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MARKET_MATCH_ENABLE','ETH_BTC_COINBASE_ENCRY',41,174,true,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MARKET_MATCH_ENABLE','ETC_BTC_COINBASE_ENCRY',41,174,true,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MARKET_MATCH_ENABLE','ETC_ETH_COINBASE_ENCRY',41,174,true,0);
-- 交易频率(毫秒-高)
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_FREQUENCY_HIGH','BTC_USDT_COINBASE_ENCRY',41,175,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_FREQUENCY_HIGH','ETH_USDT_COINBASE_ENCRY',41,175,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_FREQUENCY_HIGH','ETC_USDT_COINBASE_ENCRY',41,175,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_FREQUENCY_HIGH','ETH_BTC_COINBASE_ENCRY',41,175,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_FREQUENCY_HIGH','ETC_BTC_COINBASE_ENCRY',41,175,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_FREQUENCY_HIGH','ETC_ETH_COINBASE_ENCRY',41,175,100,0);
-- 交易频率(毫秒-低)
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_FREQUENCY_LOW','BTC_USDT_COINBASE_ENCRY',41,176,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_FREQUENCY_LOW','ETH_USDT_COINBASE_ENCRY',41,176,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_FREQUENCY_LOW','ETC_USDT_COINBASE_ENCRY',41,176,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_FREQUENCY_LOW','ETH_BTC_COINBASE_ENCRY',41,176,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_FREQUENCY_LOW','ETC_BTC_COINBASE_ENCRY',41,176,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_FREQUENCY_LOW','ETC_ETH_COINBASE_ENCRY',41,176,100,0);
-- 交易数量(高)
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_VOLUME_HIGH','BTC_USDT_COINBASE_ENCRY',41,177,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_VOLUME_HIGH','ETH_USDT_COINBASE_ENCRY',41,177,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_VOLUME_HIGH','ETC_USDT_COINBASE_ENCRY',41,177,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_VOLUME_HIGH','ETH_BTC_COINBASE_ENCRY',41,177,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_VOLUME_HIGH','ETC_BTC_COINBASE_ENCRY',41,177,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_VOLUME_HIGH','ETC_ETH_COINBASE_ENCRY',41,177,100,0);
-- 交易数量(低)
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_VOLUME_LOW','BTC_USDT_COINBASE_ENCRY',41,178,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_VOLUME_LOW','ETH_USDT_COINBASE_ENCRY',41,178,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_VOLUME_LOW','ETC_USDT_COINBASE_ENCRY',41,178,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_VOLUME_LOW','ETH_BTC_COINBASE_ENCRY',41,178,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_VOLUME_LOW','ETC_BTC_COINBASE_ENCRY',41,178,100,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_TRADING_VOLUME_LOW','ETC_ETH_COINBASE_ENCRY',41,178,100,0);
-- 单次最小卖出委托额
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_SELL_AMOUNT','BTC_USDT_COINBASE_ENCRY',41,179,0.000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_SELL_AMOUNT','ETH_USDT_COINBASE_ENCRY',41,179,0.000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_SELL_AMOUNT','ETC_USDT_COINBASE_ENCRY',41,179,0.00000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_SELL_AMOUNT','ETH_BTC_COINBASE_ENCRY',41,179,0.00000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_SELL_AMOUNT','ETC_BTC_COINBASE_ENCRY',41,179,0.00000001,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MIN_SELL_AMOUNT','ETC_ETH_COINBASE_ENCRY',41,179,0.00000001,0);
-- 单次最大卖出委托额
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_SELL_AMOUNT','BTC_USDT_COINBASE_ENCRY',41,180,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_SELL_AMOUNT','ETH_USDT_COINBASE_ENCRY',41,180,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_SELL_AMOUNT','ETC_USDT_COINBASE_ENCRY',41,180,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_SELL_AMOUNT','ETH_BTC_COINBASE_ENCRY',41,180,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_SELL_AMOUNT','ETC_BTC_COINBASE_ENCRY',41,180,1000000000,0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_MAX_SELL_AMOUNT','ETC_ETH_COINBASE_ENCRY',41,180,1000000000,0);
-- 深度合并步长
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_DEEP_MERGE_STEP','BTC_USDT_COINBASE_ENCRY',41,181,'0.01,0.1,1,10,100,1000',0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_DEEP_MERGE_STEP','ETH_USDT_COINBASE_ENCRY',41,181,'0.01,0.1,1,10,100,1000',0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_DEEP_MERGE_STEP','ETC_USDT_COINBASE_ENCRY',41,181,'0.0001,0.001,0.01,0.1,1,10',0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_DEEP_MERGE_STEP','ETH_BTC_COINBASE_ENCRY',41,181,'0.000001,0.00001,0.0001,0.001,0.01,0.1',0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_DEEP_MERGE_STEP','ETC_BTC_COINBASE_ENCRY',41,181,'0.000001,0.00001,0.0001,0.001,0.01,0.1',0);
insert into ins_parameter (code, obj_code, type, def_id, val, `version`) values ('ENCRY_DEEP_MERGE_STEP','ETC_ETH_COINBASE_ENCRY',41,181,'0.000001,0.00001,0.0001,0.001,0.01',0);