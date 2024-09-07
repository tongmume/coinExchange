#!/bin/sh
export BCH=bch-1.2.jar
export BSV=bsv-1.2.jar
export ERCEUSDT=erc-eusdt-1.2.jar
export ETH=eth-1.2.jar
export LTC=ltc-1.2.jar
export USDT=usdt-1.2.jar

export BCH_port=7006
export BSV_port=7008
export ERCEUSDT_port=7004
export ETH_port=7003
export LTC_port=7007
export USDT_port=7002

case "$1" in

start)
        ## 启动bch
        echo "--------开始启动 BCH---------------"
        nohup java -Xms512m -Xmx512m -jar $BCH >/dev/null 2>&1 &
        BCH_pid=`lsof -i:$BCH_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$BCH_pid" ]
            do
              BCH_pid=`lsof -i:$BCH_port|grep "LISTEN"|awk '{print $2}'`  
            done
        echo "BCH pid is $BCH_pid"
        echo "--------BCH 启动成功---------------"
 
        ## 启动bsv
        echo "--------开始启动 BSV---------------"
        nohup java -Xms512m -Xmx512m -jar $BSV >/dev/null 2>&1 &
        BSV_pid=`lsof -i:$BSV_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$BSV_pid" ]
            do
              BSV_pid=`lsof -i:$BSV_port|grep "LISTEN"|awk '{print $2}'`  
            done
        echo "BSV pid is $BSV_pid"
        echo "---------BSV 启动成功--------------"
 
        ## 启动erceusdt
        echo "--------开始启动 ERCEUSDT----------"
        nohup java -Xms512m -Xmx512m -jar $ERCEUSDT >/dev/null 2>&1 &
        ERCEUSDT_pid=`lsof -i:$ERCEUSDT_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$ERCEUSDT_pid" ]
            do
              ERCEUSDT_pid=`lsof -i:$ERCEUSDT_port|grep "LISTEN"|awk '{print $2}'`  
            done
        echo "ERCEUSDT pid is $ERCEUSDT_pid"
        echo "---------ERCEUSDT 启动成功---------"
                 
        ## 启动eth
        echo "--------开始启动 ETH---------------"
        nohup java -Xms512m -Xmx512m -jar $ETH >/dev/null 2>&1 &
        ETH_pid=`lsof -i:$ETH_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$ETH_pid" ]
            do
              ETH_pid=`lsof -i:$ETH_port|grep "LISTEN"|awk '{print $2}'`  
            done
        echo "ETH pid is $ETH_pid"
        echo "---------ETH 启动成功--------------"

        ## 启动ltc
        echo "--------开始启动 LTC---------------"
        nohup java -Xms512m -Xmx512m -jar $LTC >/dev/null 2>&1 &
        LTC_pid=`lsof -i:$LTC_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$LTC_pid" ]
            do
              LTC_pid=`lsof -i:$LTC_port|grep "LISTEN"|awk '{print $2}'`  
            done
        echo "LTC pid is $LTC_pid"
        echo "---------LTC 启动成功--------------"

        ## 启动usdt
        echo "--------开始启动 USDT--------------"
        nohup java -Xms512m -Xmx512m -jar $USDT >/dev/null 2>&1 &
        USDT_pid=`lsof -i:$USDT_port|grep "LISTEN"|awk '{print $2}'`
        until [ -n "$USDT_pid" ]
            do
              USDT_pid=`lsof -i:$USDT_port|grep "LISTEN"|awk '{print $2}'`  
            done
        echo "USDT pid is $USDT_pid"
        echo "---------USDT 启动成功-------------"
        
        echo "===startAll success==="
        ;;

 stop)
        P_ID=`ps -ef | grep -w $BCH | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===BCH process not exists or stop success"
        else
            kill -9 $P_ID
            echo "BCH killed success"
        fi

         P_ID=`ps -ef | grep -w $BSV | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===BSV process not exists or stop success"
        else
            kill -9 $P_ID
            echo "BSV killed success"
        fi
         P_ID=`ps -ef | grep -w $ERCEUSDT | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===ERCEUSDT process not exists or stop success"
        else
            kill -9 $P_ID
            echo "ERCEUSDT killed success"
        fi
         P_ID=`ps -ef | grep -w $ETH | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===ETH process not exists or stop success"
        else
            kill -9 $P_ID
            echo "ETH killed success"
        fi
         P_ID=`ps -ef | grep -w $LTC | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===LTC process not exists or stop success"
        else
            kill -9 $P_ID
            echo "LTC killed success"
        fi
         P_ID=`ps -ef | grep -w $USDT | grep -v "grep" | awk '{print $2}'`
        if [ "$P_ID" == "" ]; then
            echo "===USDT process not exists or stop success"
        else
            kill -9 $P_ID
            echo "USDT killed success"
        fi

        echo "===stop success==="
        ;;   
 
restart)
        $0 stop
        sleep 2
        $0 start
        echo "===restart success==="
        ;;   
esac    
exit 0