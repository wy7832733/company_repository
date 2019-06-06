/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 60005
Source Host           : localhost:3306
Source Database       : yzlhzs

Target Server Type    : MYSQL
Target Server Version : 60005
File Encoding         : 65001

Date: 2019-06-04 10:22:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_depart
-- ----------------------------
DROP TABLE IF EXISTS `s_depart`;
CREATE TABLE `s_depart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `depart_name` varchar(30) DEFAULT NULL COMMENT '部门名称',
  `intro` varchar(255) DEFAULT NULL COMMENT '部门介绍',
  `delete_flay` int(2) DEFAULT '0',
  `pid` int(11) DEFAULT NULL COMMENT '上级单位id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_depart
-- ----------------------------
INSERT INTO `s_depart` VALUES ('1', '华东局、华东油气分公司', '厅级单位', '0', '0');
INSERT INTO `s_depart` VALUES ('2', '石油技师学院', '处级单位', '0', '1');
INSERT INTO `s_depart` VALUES ('3', '石油学院', '科级单位', '0', '1');
INSERT INTO `s_depart` VALUES ('4', '测试部', '负责测试', '0', '1');
INSERT INTO `s_depart` VALUES ('15', '测试组', '11', '0', '4');

-- ----------------------------
-- Table structure for s_doc_drconf
-- ----------------------------
DROP TABLE IF EXISTS `s_doc_drconf`;
CREATE TABLE `s_doc_drconf` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '表名',
  `xmlsetting` varchar(4000) DEFAULT NULL,
  `tmpproc` varchar(100) DEFAULT NULL,
  `drids` varchar(30) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0',
  `need` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gbk COMMENT='xml导入设置表';

-- ----------------------------
-- Records of s_doc_drconf
-- ----------------------------
INSERT INTO `s_doc_drconf` VALUES ('1', '测试表', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<excel>\r\n   <sheet name=\"测试表\" code=\"s_test_a\"  head=\"0\" data=\"1\" drlsname=\"dep_id\">\r\n    <column name=\"名称\" code=\"name\" type=\"String\">\r\n    </column>\r\n   </sheet>\r\n</excel>', 'test', null, '1', '1');
INSERT INTO `s_doc_drconf` VALUES ('2', '人员信息', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<excel>\r\n   <sheet name=\"Sheet1\" code=\"s_workers\"  head=\"0\" data=\"1\" drlsname=\"dep_id\">\r\n    <column name=\"姓名\" code=\"name\" type=\"String\">\r\n    </column>\r\n    <column name=\"性别\" code=\"gender\" type=\"String\">\r\n    </column>\r\n    <column name=\"身份证号\" code=\"identify_code\" type=\"String\">\r\n    </column>\r\n    <column name=\"HR编号\" code=\"hr_code\" type=\"String\">\r\n    </column>\r\n    <column name=\"工种等级\" code=\"level\" type=\"String\">\r\n    </column>\r\n    <column name=\"职称\" code=\"title\" type=\"String\">\r\n    </column>\r\n    <column name=\"基层单位\" code=\"depart\" type=\"String\">\r\n    </column>\r\n    <column name=\"岗位\" code=\"post\" type=\"String\">\r\n    </column>\r\n    <column name=\"单位\" code=\"unit\" type=\"String\">\r\n    </column>\r\n   </sheet>\r\n</excel>', 'workers', '人员导入', '0', '1');
INSERT INTO `s_doc_drconf` VALUES ('3', '证书信息', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<excel>\r\n   <sheet name=\"证书信息\" code=\"s_certificate\"  head=\"0\" data=\"1\" drlsname=\"dep_id\">\r\n    <column name=\"证书编号\" code=\"code\" type=\"String\">\r\n    </column>\r\n    <column name=\"证书类型\" code=\"certificate_type\" type=\"String\">\r\n    </column>\r\n    <column name=\"发证日期\" code=\"release_date\" type=\"String\">\r\n    </column>\r\n    <column name=\"考试分数\" code=\"exam_points\" type=\"Integer\">\r\n    </column>\r\n    <column name=\"持证人（身份证号）\" code=\"worker\" type=\"String\">\r\n    </column>\r\n    <column name=\"发证机构\" code=\"certification_authority\" type=\"String\">\r\n    </column>\r\n    <column name=\"有效期\" code=\"effective_months\" type=\"Integer\">\r\n    </column>\r\n    <column name=\"有效期至\" code=\"end_date\" type=\"String\">\r\n    </column>\r\n   </sheet>\r\n</excel>', 'certificate', null, '0', '1');
INSERT INTO `s_doc_drconf` VALUES ('4', '井控证', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<excel>\r\n   <sheet name=\"Sheet1\" code=\"s_certificate\"  head=\"0\" data=\"1\" drlsname=\"dep_id\">\r\n    <column name=\"证书编号\" code=\"code\" type=\"String\">\r\n    </column>\r\n    <column name=\"姓名\" code=\"workerName\" type=\"String\">\r\n    </column>\r\n    <column name=\"身份证号\" code=\"worker\" type=\"String\">\r\n    </column>\r\n    <column name=\"单位\" code=\"unitText\" type=\"String\">\r\n    </column>\r\n    <column name=\"岗位\" code=\"postText\" type=\"String\">\r\n    </column>\r\n    <column name=\"培训类别\" code=\"trainType\" type=\"String\">\r\n    </column>\r\n    <column name=\"培训地点\" code=\"trainAddress\" type=\"Integer\">\r\n    </column>\r\n    <column name=\"证书有效期至\" code=\"endDate\" type=\"String\">\r\n    </column>\r\n    <column name=\"培训班编号\" code=\"trainCode\" type=\"String\">\r\n    </column>\r\n	<column name=\"培训班名称\" code=\"trainClassName\" type=\"String\">\r\n    </column>\r\n	<column name=\"发证时间\" code=\"releaseDate\" type=\"String\">\r\n    </column>\r\n	<column name=\"理论成绩\" code=\"examePoints\" type=\"Integer\">\r\n    </column>\r\n	<column name=\"操作成绩\" code=\"operatePoints\" type=\"Integer\">\r\n    </column>\r\n	<column name=\"学历\" code=\"education\" type=\"Integer\">\r\n    </column>\r\n	<column name=\"部门\" code=\"departText\" type=\"String\">\r\n    </column>\r\n	<column name=\"性别\" code=\"workerGender\" type=\"String\">\r\n    </column>\r\n	<column name=\"用工性质（正式/劳务）\" code=\"workerType\" type=\"String\">\r\n    </column>\r\n	<column name=\"HD编号\" code=\"hdCode\" type=\"Integer\">\r\n    </column>\r\n	<column name=\"培训机构名称\" code=\"trainInstitution\" type=\"String\">\r\n    </column>\r\n   </sheet>\r\n</excel>', 'certificate', '井控证', '0', '1');
INSERT INTO `s_doc_drconf` VALUES ('5', '硫化氢', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<excel>\r\n   <sheet name=\"Sheet1\" code=\"s_certificate\"  head=\"0\" data=\"1\" drlsname=\"dep_id\">\r\n    <column name=\"培训班编号\" code=\"trainCode\" type=\"String\">\r\n    </column>  \r\n	<column name=\"培训班名称\" code=\"trainClassName\" type=\"String\">\r\n    </column>	\r\n	<column name=\"证书编号\" code=\"code\" type=\"String\">\r\n    </column>\r\n    <column name=\"姓名\" code=\"workerName\" type=\"String\">\r\n    </column>\r\n	<column name=\"性别\" code=\"workerGender\" type=\"String\">\r\n    </column>\r\n    <column name=\"单位\" code=\"unitText\" type=\"String\">\r\n    </column>\r\n	<column name=\"部门\" code=\"departText\" type=\"String\">\r\n    </column>\r\n    <column name=\"岗位\" code=\"postText\" type=\"String\">\r\n    </column>\r\n    <column name=\"身份证号\" code=\"worker\" type=\"String\">\r\n    </column>\r\n	<column name=\"学历\" code=\"education\" type=\"Integer\">\r\n    </column>\r\n    <column name=\"培训地点\" code=\"trainAddress\" type=\"Integer\">\r\n    </column>\r\n    <column name=\"培训类别\" code=\"trainType\" type=\"String\">\r\n    </column>\r\n	<column name=\"发证时间\" code=\"releaseDate\" type=\"String\">\r\n    </column>\r\n    <column name=\"到期时间\" code=\"endDate\" type=\"String\">\r\n    </column>\r\n	<column name=\"理论成绩\" code=\"examePoints\" type=\"Integer\">\r\n    </column>\r\n	<column name=\"操作成绩\" code=\"operatePoints\" type=\"Integer\">\r\n    </column>\r\n	<column name=\"英文姓名\" code=\"englishName\" type=\"String\">\r\n    </column>\r\n	<column name=\"用工性质（正式/劳务）\" code=\"workerType\" type=\"String\">\r\n    </column>\r\n   </sheet>\r\n</excel>', 'certificate', '硫化氢', '0', '1');
INSERT INTO `s_doc_drconf` VALUES ('7', '培训合格证', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<excel>\r\n   <sheet name=\"Sheet1\" code=\"s_certificate\"  head=\"0\" data=\"1\" drlsname=\"dep_id\">\r\n    <column name=\"培训班编号\" code=\"trainCode\" type=\"String\">\r\n    </column>    \r\n	<column name=\"培训班名称\" code=\"trainClassName\" type=\"String\">\r\n    </column>	\r\n	<column name=\"证书编号\" code=\"code\" type=\"String\">\r\n    </column>	\r\n    <column name=\"学员姓名\" code=\"workerName\" type=\"String\">\r\n    </column>\r\n	<column name=\"性别\" code=\"workerGender\" type=\"String\">\r\n    </column>\r\n    <column name=\"单位\" code=\"unitText\" type=\"String\">\r\n    </column>\r\n	<column name=\"部门\" code=\"departText\" type=\"String\">\r\n    </column>\r\n    <column name=\"岗位\" code=\"postText\" type=\"String\">\r\n    </column>\r\n    <column name=\"身份证号\" code=\"worker\" type=\"String\">\r\n    </column>\r\n	<column name=\"学历\" code=\"education\" type=\"String\">\r\n    </column>\r\n    <column name=\"培训地点\" code=\"trainAddress\" type=\"String\">\r\n    </column>\r\n	<column name=\"发证时间\" code=\"releaseDate\" type=\"String\">\r\n    </column>\r\n    <column name=\"到期时间\" code=\"endDate\" type=\"String\">\r\n    </column>\r\n	<column name=\"理论成绩\" code=\"examPoints\" type=\"String\">\r\n    </column>\r\n	<column name=\"操作成绩\" code=\"operatePoints\" type=\"String\">\r\n    </column>\r\n    <column name=\"培训时间\" code=\"trainPeriod\" type=\"String\">\r\n    </column>\r\n    <column name=\"备注\" code=\"memo\" type=\"String\">\r\n    </column>\r\n   </sheet>\r\n</excel>', 'certificate', '培训合格证', '0', '1');
INSERT INTO `s_doc_drconf` VALUES ('8', '标准学员成绩', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<excel>\r\n   <sheet name=\"Sheet1\" code=\"s_certificate\"  head=\"3\" data=\"4\" drlsname=\"dep_id\">\r\n 	<column name=\"序号\" code=\"orderNum\" type=\"Integer\">\r\n    </column>     \r\n    <column name=\"姓名\" code=\"workerName\" type=\"String\">\r\n    </column>\r\n	<column name=\"性别\" code=\"workerGender\" type=\"String\">\r\n    </column>	\r\n    <column name=\"单位名称\" code=\"unitText\" type=\"String\">\r\n    </column>\r\n    <column name=\"岗位\" code=\"postText\" type=\"String\">\r\n    </column>\r\n    <column name=\"身份证号\" code=\"worker\" type=\"String\">\r\n    </column>\r\n	<column name=\"理论成绩\" code=\"examePoints\" type=\"Integer\">\r\n    </column>\r\n	<column name=\"实操成绩\" code=\"operatePoints\" type=\"Integer\">\r\n    </column>\r\n    <column name=\"备注\" code=\"memo\" type=\"String\">\r\n    </column>\r\n   </sheet>\r\n</excel>', 'certificate', '标准学员成绩', '0', '1');
INSERT INTO `s_doc_drconf` VALUES ('9', '境外公共安全', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<excel>\r\n   <sheet name=\"Sheet1\" code=\"s_certificate\"  head=\"0\" data=\"1\" drlsname=\"dep_id\">\r\n    <column name=\"序号\" code=\"orderNum\" type=\"Integer\">\r\n    </column>\r\n    <column name=\"单位\" code=\"unitText\" type=\"String\">\r\n    </column>\r\n    <column name=\"姓名\" code=\"workerName\" type=\"String\">\r\n    </column>\r\n    <column name=\"身份证号\" code=\"worker\" type=\"String\">\r\n    </column>\r\n    <column name=\"职位\" code=\"postText\" type=\"String\">\r\n    </column>\r\n	<column name=\"证书编号\" code=\"code\" type=\"String\">\r\n    </column>\r\n	<column name=\"性别\" code=\"workerGender\" type=\"String\">\r\n    </column>\r\n    <column name=\"培训地点\" code=\"trainAddress\" type=\"Integer\">\r\n    </column>\r\n	<column name=\"发照日期\" code=\"releaseDate\" type=\"String\">\r\n    </column>\r\n    <column name=\"有效期\" code=\"endDate\" type=\"String\">\r\n    </column>\r\n	<column name=\"理论成绩\" code=\"examePoints\" type=\"String\">\r\n    </column>\r\n	<column name=\"实操成绩\" code=\"operatePoints\" type=\"String\">\r\n    </column>\r\n	<column name=\"综合成绩\" code=\"totalPoints\" type=\"String\">\r\n    </column>\r\n    <column name=\"初/复训\" code=\"trainNature\" type=\"String\">\r\n    </column>\r\n   </sheet>\r\n</excel>', 'certificate', '境外公共安全', '0', '1');

-- ----------------------------
-- Table structure for s_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_permission`;
CREATE TABLE `s_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `resource_type` varchar(255) DEFAULT NULL COMMENT '资源类型 [menu|button]',
  `url` varchar(255) DEFAULT NULL COMMENT '资源路径',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view',
  `parent_id` int(11) DEFAULT NULL COMMENT '父编号',
  `parent_ids` varchar(255) DEFAULT NULL COMMENT ' 父编号列表',
  `available` varchar(200) DEFAULT NULL,
  `fontawesome` varchar(100) DEFAULT NULL COMMENT '样式',
  `sorder` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=50248 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_permission
-- ----------------------------
INSERT INTO `s_permission` VALUES ('101', '用户管理', 'menu', 'user/userManage', 'user:view', '1', '0/', '1', '', null);
INSERT INTO `s_permission` VALUES ('1001', '用户添加', 'button', 'user/userAdd', 'user:add', '101', '0/1', '1', null, null);
INSERT INTO `s_permission` VALUES ('1002', '用户修改', 'button', 'user/userAdd', 'user:edit', '101', '0/1', '1', null, null);
INSERT INTO `s_permission` VALUES ('1003', '用户删除', 'button', 'user/deleteUsers', 'user:delete', '101', '0/1', '1', null, null);
INSERT INTO `s_permission` VALUES ('1', '系统管理', 'menu', '', 'user:view', null, '', '1', 'icon-asterisk', '99999');
INSERT INTO `s_permission` VALUES ('102', '单位管理', 'menu', 'depart/departManage', 'depart:view', '1', '0/', '1', null, null);
INSERT INTO `s_permission` VALUES ('1021', '单位添加', 'button', 'depart/departAdd', 'depart:add', '102', '0/1', '1', null, null);
INSERT INTO `s_permission` VALUES ('1022', '单位修改', 'button', 'depart/departEdit', 'depart:edit', '102', '0/1', '1', null, null);
INSERT INTO `s_permission` VALUES ('1023', '单位删除', 'button', 'depart/deleteDepart', 'depart:delete', '102', '0/1', '1', null, null);
INSERT INTO `s_permission` VALUES ('104', '角色管理', 'menu', 'role/roleManage', 'role:view', '1', '0/', '1', null, null);
INSERT INTO `s_permission` VALUES ('1041', '角色添加', 'button', 'role/roleAdd', 'role:add', '104', '0/1', '1', null, null);
INSERT INTO `s_permission` VALUES ('1042', '角色修改', 'button', 'role/roleEdit', 'role:edit', '104', '0/1', '1', null, null);
INSERT INTO `s_permission` VALUES ('1043', '角色删除', 'button', 'role/roleDelete', 'role:delete', '104', '0/1', '1', null, null);
INSERT INTO `s_permission` VALUES ('905', '班级管理', 'menu', 'classInfo/classInfoManage', 'classInfo:view', '9', '0/', '1', '', null);
INSERT INTO `s_permission` VALUES ('9', '证书管理', 'menu', '', 'user:view', null, '', '1', 'icon-bookmark', '11');
INSERT INTO `s_permission` VALUES ('902', '人员管理', 'menu', 'workers/workersManage', 'workers:view', '9', '0/', '1', '', null);
INSERT INTO `s_permission` VALUES ('9021', '人员添加', 'button', 'workers/workersAdd', 'workers:add', '902', '0/1', '1', '', null);
INSERT INTO `s_permission` VALUES ('9022', '人员修改', 'button', 'workers/workersEdit', 'workers:edit', '902', '0/1', '1', '', null);
INSERT INTO `s_permission` VALUES ('9023', '人员删除', 'button', 'workers/deleteWorkers', 'workers:delete', '902', '0/1', '1', '', null);
INSERT INTO `s_permission` VALUES ('903', '证书类型管理', 'menu', 'certificateType/certificateTypeManage', 'certificateType:view', '9', '0/', '1', '', null);
INSERT INTO `s_permission` VALUES ('9031', '证书类型添加', 'button', 'certificateType/certificateTypeAdd', 'certificateType:add', '903', '0/1', '1', '', null);
INSERT INTO `s_permission` VALUES ('9032', '证书类型修改', 'button', 'certificateType/certificateTypeEdit', 'certificateType:edit', '903', '0/1', '1', '', null);
INSERT INTO `s_permission` VALUES ('9033', '证书类型删除', 'button', 'certificateType/deleteCertificateType', 'certificateType:delete', '903', '0/1', '1', '', null);
INSERT INTO `s_permission` VALUES ('904', '证书管理', 'menu', 'certificate/certificateManage', 'certificate:view', '9', '0/', '1', '', null);
INSERT INTO `s_permission` VALUES ('9041', '证书添加', 'button', 'certificate/certificateAdd', 'certificate:add', '904', '0/1', '1', '', null);
INSERT INTO `s_permission` VALUES ('9042', '证书修改', 'button', 'certificate/certificateEdit', 'certificate:edit', '904', '0/1', '1', '', null);
INSERT INTO `s_permission` VALUES ('9043', '证书删除', 'button', 'certificate/deleteCertificate', 'certificate:delete', '904', '0/1', '1', '', null);
INSERT INTO `s_permission` VALUES ('11', '证书应用', 'menu', '', 'user:view', null, '', '1', 'icon-tasks', '12');
INSERT INTO `s_permission` VALUES ('1101', '统计分析', 'menu', 'certificate/statisticsManage', 'statistics:view', '11', '0/', '1', '', null);
INSERT INTO `s_permission` VALUES ('1102', '证书到期预警', 'menu', 'certificate/certificateExpireManage', 'certificateExpire:view', '11', '0/', '1', '', null);
INSERT INTO `s_permission` VALUES ('1103', '年度培训计划', 'menu', 'certificate/trainPlanManage', 'trainPlan:view', '11', '0/', '1', '', null);
INSERT INTO `s_permission` VALUES ('1104', '现场检查应用', 'menu', 'depart/fieldInspectionManage', 'fieldInspection:view', '11', '0/', '1', '', null);
INSERT INTO `s_permission` VALUES ('107', '岗位管理', 'menu', 'post/postManage', 'post:view', '1', '0/', '1', '', null);
INSERT INTO `s_permission` VALUES ('1071', '岗位添加', 'button', 'post/postAdd', 'post:add', '107', '0/1', '1', '', null);
INSERT INTO `s_permission` VALUES ('1072', '岗位修改', 'button', 'post/postEdit', 'post:edit', '107', '0/1', '1', '', null);
INSERT INTO `s_permission` VALUES ('1073', '岗位删除', 'button', 'post/deletePost', 'post:delete', '107', '0/1', '1', '', null);

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色标识程序中判断使用,如"admin",这个是唯一的',
  `role` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述,UI界面显示使用',
  `available` varchar(200) DEFAULT '1',
  `delete_flag` int(1) DEFAULT '0',
  `visual_dep` varchar(255) DEFAULT NULL COMMENT '可见部门',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('11', '测试', '1111111111', '1', '0', '1,2,3,4,15');
INSERT INTO `s_role` VALUES ('10', '超级管理员', '调试', '1', '0', '1,2,3,4,15');

-- ----------------------------
-- Table structure for s_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_role_permission`;
CREATE TABLE `s_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=659 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role_permission
-- ----------------------------
INSERT INTO `s_role_permission` VALUES ('658', '10', '905');
INSERT INTO `s_role_permission` VALUES ('657', '11', '1104');
INSERT INTO `s_role_permission` VALUES ('656', '11', '1103');
INSERT INTO `s_role_permission` VALUES ('655', '11', '1102');
INSERT INTO `s_role_permission` VALUES ('654', '11', '1101');
INSERT INTO `s_role_permission` VALUES ('653', '11', '11');
INSERT INTO `s_role_permission` VALUES ('652', '11', '9043');
INSERT INTO `s_role_permission` VALUES ('651', '11', '9042');
INSERT INTO `s_role_permission` VALUES ('650', '11', '9041');
INSERT INTO `s_role_permission` VALUES ('649', '11', '904');
INSERT INTO `s_role_permission` VALUES ('648', '11', '9033');
INSERT INTO `s_role_permission` VALUES ('647', '11', '9032');
INSERT INTO `s_role_permission` VALUES ('646', '11', '9031');
INSERT INTO `s_role_permission` VALUES ('645', '11', '903');
INSERT INTO `s_role_permission` VALUES ('644', '11', '9023');
INSERT INTO `s_role_permission` VALUES ('643', '11', '9022');
INSERT INTO `s_role_permission` VALUES ('642', '11', '9021');
INSERT INTO `s_role_permission` VALUES ('641', '11', '902');
INSERT INTO `s_role_permission` VALUES ('640', '11', '9');
INSERT INTO `s_role_permission` VALUES ('639', '11', '1073');
INSERT INTO `s_role_permission` VALUES ('638', '11', '1072');
INSERT INTO `s_role_permission` VALUES ('637', '11', '1071');
INSERT INTO `s_role_permission` VALUES ('636', '11', '107');
INSERT INTO `s_role_permission` VALUES ('635', '11', '1043');
INSERT INTO `s_role_permission` VALUES ('634', '11', '1042');
INSERT INTO `s_role_permission` VALUES ('633', '11', '1041');
INSERT INTO `s_role_permission` VALUES ('632', '11', '104');
INSERT INTO `s_role_permission` VALUES ('631', '11', '1023');
INSERT INTO `s_role_permission` VALUES ('630', '11', '1022');
INSERT INTO `s_role_permission` VALUES ('629', '11', '1021');
INSERT INTO `s_role_permission` VALUES ('628', '11', '102');
INSERT INTO `s_role_permission` VALUES ('627', '11', '1003');
INSERT INTO `s_role_permission` VALUES ('626', '11', '1002');
INSERT INTO `s_role_permission` VALUES ('625', '11', '1001');
INSERT INTO `s_role_permission` VALUES ('624', '11', '101');
INSERT INTO `s_role_permission` VALUES ('623', '11', '1');
INSERT INTO `s_role_permission` VALUES ('587', '10', '1104');
INSERT INTO `s_role_permission` VALUES ('586', '10', '1103');
INSERT INTO `s_role_permission` VALUES ('585', '10', '1102');
INSERT INTO `s_role_permission` VALUES ('584', '10', '1101');
INSERT INTO `s_role_permission` VALUES ('583', '10', '11');
INSERT INTO `s_role_permission` VALUES ('582', '10', '9043');
INSERT INTO `s_role_permission` VALUES ('581', '10', '9042');
INSERT INTO `s_role_permission` VALUES ('580', '10', '9041');
INSERT INTO `s_role_permission` VALUES ('579', '10', '904');
INSERT INTO `s_role_permission` VALUES ('578', '10', '9033');
INSERT INTO `s_role_permission` VALUES ('577', '10', '9032');
INSERT INTO `s_role_permission` VALUES ('576', '10', '9031');
INSERT INTO `s_role_permission` VALUES ('575', '10', '903');
INSERT INTO `s_role_permission` VALUES ('574', '10', '9023');
INSERT INTO `s_role_permission` VALUES ('573', '10', '9022');
INSERT INTO `s_role_permission` VALUES ('572', '10', '9021');
INSERT INTO `s_role_permission` VALUES ('571', '10', '902');
INSERT INTO `s_role_permission` VALUES ('570', '10', '9');
INSERT INTO `s_role_permission` VALUES ('569', '10', '1073');
INSERT INTO `s_role_permission` VALUES ('568', '10', '1072');
INSERT INTO `s_role_permission` VALUES ('567', '10', '1071');
INSERT INTO `s_role_permission` VALUES ('566', '10', '107');
INSERT INTO `s_role_permission` VALUES ('565', '10', '1043');
INSERT INTO `s_role_permission` VALUES ('564', '10', '1042');
INSERT INTO `s_role_permission` VALUES ('563', '10', '1041');
INSERT INTO `s_role_permission` VALUES ('562', '10', '104');
INSERT INTO `s_role_permission` VALUES ('561', '10', '1023');
INSERT INTO `s_role_permission` VALUES ('560', '10', '1022');
INSERT INTO `s_role_permission` VALUES ('559', '10', '1021');
INSERT INTO `s_role_permission` VALUES ('558', '10', '102');
INSERT INTO `s_role_permission` VALUES ('557', '10', '1003');
INSERT INTO `s_role_permission` VALUES ('556', '10', '1002');
INSERT INTO `s_role_permission` VALUES ('555', '10', '1001');
INSERT INTO `s_role_permission` VALUES ('554', '10', '101');
INSERT INTO `s_role_permission` VALUES ('553', '10', '1');

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) DEFAULT NULL COMMENT '账号',
  `password` varchar(40) DEFAULT NULL COMMENT '密码',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐',
  `state` int(2) DEFAULT '0' COMMENT '用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 ,',
  `p_id` int(5) DEFAULT NULL COMMENT '部门id',
  `area_id` int(3) DEFAULT NULL COMMENT '区域id s_area',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `login_key` varchar(255) DEFAULT '' COMMENT '登陆成功生成app端key',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `delete_flay` int(2) DEFAULT '0' COMMENT '是否删除 0-未删除 1-删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', 'admin', '111111', '管理员', null, '0', '1', '1', '15161872679', '', '15161872679@163.com', '0');
INSERT INTO `s_user` VALUES ('8', 'test1', '123456', '测试1', null, '0', '15', null, '15850424453', null, '1006311489@qq.com', '0');
INSERT INTO `s_user` VALUES ('9', 'wy', '123456', 'test2111', null, null, '2', null, '15850424453', null, '1006311487@qq.com8', '0');

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES ('1', '1', '10');
INSERT INTO `s_user_role` VALUES ('13', '8', '11');
INSERT INTO `s_user_role` VALUES ('12', '9', '11');
