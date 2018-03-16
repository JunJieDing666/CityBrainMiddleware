package com.djj.middleware.resource.POJOResource;

import java.util.List;

public class StatisticData {

    /**
     * InterSectionID : 123
     * DirNum : 1
     * DirList : [{"RadarNo":"123","LaneNum":2,"LaneList":[{"LaneID":1,"Volume":12,"Volume1":0,"Volume2":1,"Volume3":6,"Volume4":2,"Volume5":3,"Speed":35.7,"Occupation":12.6,"Headway":3.5,"85Speed":46.7,"Gap":3.1,"Queue":50},{"LaneID":2,"Volume":12,"Volume1":0,"Volume2":1,"Volume3":6,"Volume4":2,"Volume5":3,"Speed":35.7,"Occupation":12.6,"Headway":3.5,"85Speed":46.7,"Gap":3.1,"Queue":50}]}]
     */

    private String InterSectionID;
    private int DirNum;
    private List<DirListBean> DirList;

    public String getInterSectionID() {
        return InterSectionID;
    }

    public void setInterSectionID(String InterSectionID) {
        this.InterSectionID = InterSectionID;
    }

    public int getDirNum() {
        return DirNum;
    }

    public void setDirNum(int DirNum) {
        this.DirNum = DirNum;
    }

    public List<DirListBean> getDirList() {
        return DirList;
    }

    public void setDirList(List<DirListBean> DirList) {
        this.DirList = DirList;
    }

    public static class DirListBean {
        /**
         * RadarNo : 123
         * LaneNum : 2
         * LaneList : [{"LaneID":1,"Volume":12,"Volume1":0,"Volume2":1,"Volume3":6,"Volume4":2,"Volume5":3,"Speed":35.7,"Occupation":12.6,"Headway":3.5,"85Speed":46.7,"Gap":3.1,"Queue":50},{"LaneID":2,"Volume":12,"Volume1":0,"Volume2":1,"Volume3":6,"Volume4":2,"Volume5":3,"Speed":35.7,"Occupation":12.6,"Headway":3.5,"85Speed":46.7,"Gap":3.1,"Queue":50}]
         */

        private String RadarNo;
        private int LaneNum;
        private List<LaneListBean> LaneList;

        public String getRadarNo() {
            return RadarNo;
        }

        public void setRadarNo(String RadarNo) {
            this.RadarNo = RadarNo;
        }

        public int getLaneNum() {
            return LaneNum;
        }

        public void setLaneNum(int LaneNum) {
            this.LaneNum = LaneNum;
        }

        public List<LaneListBean> getLaneList() {
            return LaneList;
        }

        public void setLaneList(List<LaneListBean> LaneList) {
            this.LaneList = LaneList;
        }

        public static class LaneListBean {
            /**
             * LaneID : 1
             * Volume : 12
             * Volume1 : 0
             * Volume2 : 1
             * Volume3 : 6
             * Volume4 : 2
             * Volume5 : 3
             * Speed : 35.7
             * Occupation : 12.6
             * Headway : 3.5
             * 85Speed : 46.7
             * Gap : 3.1
             * Queue : 50
             */

            private int LaneID;
            private int Volume;
            private int Volume1;
            private int Volume2;
            private int Volume3;
            private int Volume4;
            private int Volume5;
            private float Speed;
            private float Occupation;
            private float Headway;
            private float Speed_85;
            private float Gap;
            private float Queue;

            public int getLaneID() {
                return LaneID;
            }

            public void setLaneID(int LaneID) {
                this.LaneID = LaneID;
            }

            public int getVolume() {
                return Volume;
            }

            public void setVolume(int Volume) {
                this.Volume = Volume;
            }

            public int getVolume1() {
                return Volume1;
            }

            public void setVolume1(int Volume1) {
                this.Volume1 = Volume1;
            }

            public int getVolume2() {
                return Volume2;
            }

            public void setVolume2(int Volume2) {
                this.Volume2 = Volume2;
            }

            public int getVolume3() {
                return Volume3;
            }

            public void setVolume3(int Volume3) {
                this.Volume3 = Volume3;
            }

            public int getVolume4() {
                return Volume4;
            }

            public void setVolume4(int Volume4) {
                this.Volume4 = Volume4;
            }

            public int getVolume5() {
                return Volume5;
            }

            public void setVolume5(int Volume5) {
                this.Volume5 = Volume5;
            }

            public float getSpeed() {
                return Speed;
            }

            public void setSpeed(float Speed) {
                this.Speed = Speed;
            }

            public float getOccupation() {
                return Occupation;
            }

            public void setOccupation(float Occupation) {
                this.Occupation = Occupation;
            }

            public float getHeadway() {
                return Headway;
            }

            public void setHeadway(float Headway) {
                this.Headway = Headway;
            }

            public float getSpeed_85() {
                return Speed_85;
            }

            public void setSpeed_85(float Speed_85) {
                this.Speed_85 = Speed_85;
            }

            public float getGap() {
                return Gap;
            }

            public void setGap(float Gap) {
                this.Gap = Gap;
            }

            public float getQueue() {
                return Queue;
            }

            public void setQueue(float Queue) {
                this.Queue = Queue;
            }
        }
    }
}
