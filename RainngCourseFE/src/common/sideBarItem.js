import UserType from "./userType";
import Permission from "./permission";

let SideBarItem = {};

SideBarItem.items = [

  {
    icon: "el-icon-fa fa-bar-chart",
    index: "/core/teamBuild",
    title: "组长",
    userType: UserType.student,
    permission: "admin"
  },
  {
    icon: "el-icon-fa fa-bar-chart",
    index: "/core/teamBI",
    title: "成员",
    userType: UserType.student,
    permission: Permission.no
  },
  {
    icon: "el-icon-fa fa-bar-chart",
    index: "/core/BI",
    title: "报表",
    userType: UserType.student,
    permission: "admin"
  }

];

export default SideBarItem;
