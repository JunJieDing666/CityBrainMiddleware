# 城市大脑中间件（CityBrainMiddleware）
**A middleware for platform of Ali city brain.**

**It can be used to transmit the traffic data from radars which were settled in advance at the intersections, and the infomation would be quite useful after being analyzed.**

&emsp;&emsp;这两年兴起了**城市大脑**这一新概念，其实本质就是从种交通信息感知设备获得数据，然后对信息进行分析，最后做出利于城市交通、安全的决策。本项目正是基于此而开发的，意在于从道路交叉口的雷达中获取感知到的车辆数目、大小、速度、所在车道等非常多有利于分析交叉口的信息，然后将这些有用的数据信息存储在本地，并上传到城市大脑，以供决策。项目本质是做了一个RESTful的中间件服务，涉及到了**RESTful服务建立、数据库操作、TCP传输（长短连接、断包粘包处理等）、数据分析处理等多种技术**，涵盖了中间件的大部分功能，仅供参考。
