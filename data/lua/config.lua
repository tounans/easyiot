module(...,package.seeall)

pinsConfig = 
{
    {
        gpio= 30 ,
        method = 1, --1输出 0读
        default=1
    },
    {
        gpio= 7 ,
        method = 0
    }
}


uartConfig = {
    {
        method = 1, --1读  0写
        id=1,
        baud=9600,
        databits=8,
        parity=2,
        stopbits=0
    }

}

