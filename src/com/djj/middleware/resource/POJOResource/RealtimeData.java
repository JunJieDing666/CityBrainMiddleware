package com.djj.middleware.resource.POJOResource;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class RealtimeData {

    /**
     * InterSectionID : 123
     * DirNum : 1
     * DirList : [{"RadarNo":"123","LaneNum":2,"LaneList":[{"LaneID":1,"ObjNum":3,"ObjList":[{"ObjID":1,"ObjPos":25,"ObjSpeed":24.7,"ObjLength":4.5},{"ObjID":2,"ObjPos":14,"ObjSpeed":24.7,"ObjLength":4.5}]},{"LaneID":2,"ObjNum":2,"ObjList":[{"ObjID":1,"ObjPos":25,"ObjSpeed":24.7,"ObjLength":4.5},{"ObjID":2,"ObjPos":14,"ObjSpeed":24.7,"ObjLength":4.5}]}]}]
     */

    private String InterSectionID;
    private int DirNum;
    private List<DirListBean> DirList;

    public RealtimeData() {
    }

    public RealtimeData(String interSectionID, int dirNum, List<DirListBean> dirList) {
        InterSectionID = interSectionID;
        DirNum = dirNum;
        DirList = dirList;
    }

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
         * LaneList : [{"LaneID":1,"ObjNum":3,"ObjList":[{"ObjID":1,"ObjPos":25,"ObjSpeed":24.7,"ObjLength":4.5},{"ObjID":2,"ObjPos":14,"ObjSpeed":24.7,"ObjLength":4.5}]},{"LaneID":2,"ObjNum":2,"ObjList":[{"ObjID":1,"ObjPos":25,"ObjSpeed":24.7,"ObjLength":4.5},{"ObjID":2,"ObjPos":14,"ObjSpeed":24.7,"ObjLength":4.5}]}]
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
             * ObjNum : 3
             * ObjList : [{"ObjID":1,"ObjPos":25,"ObjSpeed":24.7,"ObjLength":4.5},{"ObjID":2,"ObjPos":14,"ObjSpeed":24.7,"ObjLength":4.5}]
             */

            private int LaneID;
            private int ObjNum;
            private List<ObjListBean> ObjList;

            public int getLaneID() {
                return LaneID;
            }

            public void setLaneID(int LaneID) {
                this.LaneID = LaneID;
            }

            public int getObjNum() {
                return ObjNum;
            }

            public void setObjNum(int ObjNum) {
                this.ObjNum = ObjNum;
            }

            public List<ObjListBean> getObjList() {
                return ObjList;
            }

            public void setObjList(List<ObjListBean> ObjList) {
                this.ObjList = ObjList;
            }

            public static class ObjListBean {
                /**
                 * ObjID : 1
                 * ObjPos : 25
                 * ObjSpeed : 24.7
                 * ObjLength : 4.5
                 */

                private int ObjID;
                private float ObjPos;
                private float ObjSpeed;
                private float ObjLength;

                public int getObjID() {
                    return ObjID;
                }

                public void setObjID(int ObjID) {
                    this.ObjID = ObjID;
                }

                public float getObjPos() {
                    return ObjPos;
                }

                public void setObjPos(float ObjPos) {
                    this.ObjPos = ObjPos;
                }

                public double getObjSpeed() {
                    return ObjSpeed;
                }

                public void setObjSpeed(float ObjSpeed) {
                    this.ObjSpeed = ObjSpeed;
                }

                public double getObjLength() {
                    return ObjLength;
                }

                public void setObjLength(float ObjLength) {
                    this.ObjLength = ObjLength;
                }
            }
        }
    }
}
