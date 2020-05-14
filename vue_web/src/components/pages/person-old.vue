<template>
  <div class="personOldDiv" id="personOldId">

    <h2>我发布的农产品：</h2>
    <!--数据列表-->
    <!--根据商品名搜索-->
    <el-table
      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase())).slice((currentPage-1)*pageSize,currentPage*pageSize)"
      style="width: 100%">
<!--      <el-table-column-->
<!--        label="商品ID"-->
<!--        prop="goodsId">-->
<!--      </el-table-column>-->
      <el-table-column
        label="农产品图">
        <template slot-scope="scope">
          <img :src="scope.row.picture" alt="加载中..." width="80" height="80">
        </template>
      </el-table-column>
      <el-table-column
        label="农产品名"
        prop="name">
      </el-table-column>
      <el-table-column
        label="价格"
        prop="price">
      </el-table-column>
      <el-table-column
        label="数量"
        prop="goodsLevel">
      </el-table-column>
      <el-table-column
        label="热度"
        prop="hot">
      </el-table-column>
      <el-table-column
        align="right">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入农产品名搜索"/>
        </template>
        <template slot-scope="scope">

          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"  >修改介绍</el-button>
          <el-button
            size="mini"
            @click="handleEdit2(scope.$index, scope.row)">取消出售</el-button>
          <el-button
            size="mini"
            @click="handlePlan(scope.$index, scope.row)" style="margin-top: 15px">种植计划</el-button>
          <el-button
            size="mini"
            @click="goQRcode(scope.row.goodsId)"  style="margin-right: 9px">二维码</el-button>
        </template>

      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20, 40]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableData.length">
    </el-pagination>


    <!--      商品介绍弹出框-->
    <el-dialog
      title="农产品信息"
      :visible.sync="centerDialogVisible"
      width="30%"
      center>
      <div style="text-align: center">
        <el-form :model="goodsForm">
          <!--<el-form-item label="商品ID" label-position="top">-->
            <!--<el-input v-model="goodsForm.goodsId" autocomplete="off" disabled></el-input>-->
          <!--</el-form-item>-->
          <el-form-item label="农产品名称" label-position="top">
            <el-input v-model="goodsForm.name" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="农产品价格" label-position="top">
            <el-input
              v-model="goodsForm.price"
            >
            </el-input>
          </el-form-item>
          <el-form-item label="成熟日期" label-position="top">
            <el-date-picker
              v-model="goodsForm.date"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="农产品类型" label-position="top">
            <el-select v-model="goodsForm.type" placeholder="请选择" >
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="农产品产地" label-position="top">
            <el-input
              type="textarea"
              placeholder="请输入农产品产地"
              v-model="goodsForm.origin"
              maxlength="250"
              show-word-limit
            >
            </el-input>
          </el-form-item>
          <el-form-item label="农产品介绍" label-position="top">
            <el-input
              type="textarea"
              placeholder="请输入农产品介绍"
              v-model="goodsForm.dscrip"
              maxlength="250"
              show-word-limit
            >
            </el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitDscrip" style="margin-top: 30px;">保存</el-button>
        </span>
      </div>
    </el-dialog>

    <!--      种植计划弹出框-->
    <el-dialog
      title="种植计划"
      :visible.sync="centerDialogVisible3"
      width="30%"
      center>
      <div style="text-align: center">
        <el-form :model="planForm">
          <!--<el-form-item label="商品ID" label-position="top">-->
          <!--<el-input v-model="goodsForm.goodsId" autocomplete="off" disabled></el-input>-->
          <!--</el-form-item>-->
          <el-form-item label="农产品名称" label-position="top">
            <el-input v-model="planForm.name" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="开始日期" label-position="top">
            <el-date-picker
              v-model="planForm.startdate"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="结束日期" label-position="top">
            <el-date-picker
              v-model="planForm.enddate"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="数量" label-position="top">
            <el-input v-model="planForm.number" ></el-input>
          </el-form-item>
          <el-form-item label="土地类型" label-position="top">
            <el-input
              v-model="planForm.land"
            >
            </el-input>
          </el-form-item>
          <el-form-item label="地区" label-position="top">
            <el-input
              v-model="planForm.area"
            >
            </el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitPlan" style="margin-top: 30px;">保存</el-button>
        </span>
      </div>

    </el-dialog>

    <!--<el-dialog-->
      <!--title="二维码"-->
      <!--:visible.sync="centerDialogVisible4"-->
      <!--width="30%" height="300px"-->
      <!--center>-->
      <!--<div>-->
      <!--<div class="qrcode" ref="qrCodeUrl"></div>-->

      <!--</div>-->

    <!--</el-dialog>-->

    <!--      弹出提示框-->
    <el-dialog
      title="提示"
      :visible.sync="centerDialogVisible2"
      width="30%"
      center>
      <div style="text-align: center">
        <h4>{{ dialogValue }}</h4>
        <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="clickButton" style="margin-top: 30px;">确 定</el-button>
        </span>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import VueQr from 'vue-qr'
  import QRCode from 'qrcodejs2'
    export default {
      name: "person-old",
      comments:{VueQr},
      data(){
        return{
          tableData: [],
          search: '',
          currentPage:1, //初始页
          pageSize:5, //每页的数据
          goodsForm:{
            goodsId:"",
            name:"",
            dscrip:"",
            price:"",
            origin:"",
            date:"",
            type:""
          },
          planForm: {
            planId: "",
            goodsId: "",
            startdate: new Date(),
            enddate: new Date(),
            number: "0",
            land: "",
            area:"",
          },
          centerDialogVisible: false,
          centerDialogVisible2: false,
          centerDialogVisible3: false,
          centerDialogVisible4: false,
          dialogValue:"",
          options: [{
            value: '1',
            label: '果蔬'
          }, {
            value: '2',
            label: '肉类'
          }, {
            value: '3',
            label: '农副产品'
          }, {
            value: '4',
            label: '作物'
          }, {
            value: '5',
            label: '其他'
          }],
          value:"",
          wanghzi:"www.baidu.com",
        }
      },
      mounted() {
        //加载数据
        let jsonObj = {};
        jsonObj.userId = window.sessionStorage.getItem("userId");
        let jsonMsg = JSON.stringify(jsonObj);
        let self = this;
        //加载文字数据
        $.get("http://localhost:8083/goods/getMyGoods.do",jsonObj,function (data) {
          self.tableData = data;
          //为每个表格元素加载图片数据，主图
          $(self.tableData).each(function (index,element) {
            let jsonObj = {};
            jsonObj.goodsId = element.goodsId;
            $.get("http://localhost:8083/goods/getGoodsMainImg.do",jsonObj,function (data) {
              //本地映射到9090端口,服务端返回的imgUrl应该为相对路径，这里图片名字就行
              element.picture = "http://localhost:9090/" + data.imgUrl;
              //因为数组单值更新不会引起 Vue 重新渲染，手动通知 Vue 渲染
              self.$set(self.tableData,index,element);
            },"json");
          });
        },"json");
        // this.creatQrCode();
      },
      methods:{
        // 初始页currentPage、初始每页数据数pageSize和数据data
        handleSizeChange: function (size) {
          this.pageSize = size;
          // console.log(this.pageSize)  //每页下拉显示数据
        },
        handleCurrentChange: function(currentPage){
          this.currentPage = currentPage;
          // console.log(this.currentPage)  //点击第几页
        },
        handleEdit(index, row) {
          //点击了商品介绍按钮
          // console.log(index, row);
          let jsonObj = {};
          jsonObj.goodsId = row.goodsId;
          let jsonMsg = JSON.stringify(jsonObj);
          let self = this;
          $.get("http://localhost:8083/goods/getGoodsDscrip.do",jsonObj,function (data) {
            if(data !== "" || data !== null) {
              self.goodsForm.goodsId = data.goodsId;
              self.goodsForm.name = data.name;
              self.goodsForm.dscrip = data.dscrip;
              self.goodsForm.date = data.date;
              self.goodsForm.price = data.price;
              self.goodsForm.origin = data.origin;
              self.goodsForm.type = data.type.toString();
              self.centerDialogVisible = true;
            }
          },"json");
        },
        handleEdit2(index, row) {
          //点击了取消出售按钮
          // console.log(index, row);
          let jsonObj = {};
          jsonObj.goodsId = row.goodsId;
          let jsonMsg = JSON.stringify(jsonObj);
          let self = this;
          $.post("http://localhost:8083/goods/deleteMyGoodsAndUrls.do",jsonMsg,function (data) {
            if(data.code === 1){
              self.dialogValue = "删除成功";
            }else{
              self.dialogValue = "删除失败，错误码：" + data.code;
            }
            self.centerDialogVisible2 = true;
          },"json");
          window.location.reload();
        },
        submitDscrip(){
          //点击了保存按钮
          let jsonObj = {};
          jsonObj.goodsId = this.goodsForm.goodsId;
          jsonObj.dscrip = this.goodsForm.dscrip;
          jsonObj.origin = this.goodsForm.origin;
          jsonObj.date = this.goodsForm.date;
          jsonObj.price = this.goodsForm.price;
          jsonObj.type = this.goodsForm.type;
          let jsonMsg = JSON.stringify(jsonObj);
          let self = this;
          $.post("http://localhost:8083/goods/saveGoodsDscrip.do",jsonMsg,function (data) {
            if(data.code === 1){
              self.dialogValue = "保存成功";
            }else{
              self.dialogValue = "保存失败，错误码：" + data.code;
            }
            self.centerDialogVisible2 = true;
          },"json");
          window.location.reload();
        },
        clickButton(){
          this.centerDialogVisible2 = false;
          this.centerDialogVisible = false;
          this.centerDialogVisible3 = false;
          this.centerDialogVisible4 = false;
        },
        handlePlan(index, row) {
          //点击了种植计划按钮
          // console.log(index, row);
          let jsonObj = {};
          jsonObj.goodsId = row.goodsId;
          let jsonMsg = JSON.stringify(jsonObj);
          let self = this;
          $.get("http://localhost:8083/plan/getPlan.do",jsonObj,function (data) {
            if(data !== "" || data !== null) {
              self.planForm.planId = data.planId;
              self.planForm.goodsId = data.goodsId;
              self.planForm.startdate = data.startdate;
              self.planForm.enddate = data.enddate;
              self.planForm.land = data.land;
              self.planForm.area = data.area;
              self.planForm.number = data.number;
              self.planForm.name = data.name;
              self.centerDialogVisible3 = true;
            }
          },"json");
        },
        submitPlan(){
          //点击了保存按钮
          let jsonObj = {};
          jsonObj.planId = this.planForm.planId;
          jsonObj.goodsId = this.planForm.goodsId;
          jsonObj.startdate = this.planForm.startdate;
          jsonObj.enddate = this.planForm.enddate;
          jsonObj.land = this.planForm.land;
          jsonObj.area = this.planForm.area;
          jsonObj.number = this.planForm.number;
          jsonObj.name = this.planForm.name;
          let jsonMsg = JSON.stringify(jsonObj);
          let self = this;
          $.post("http://localhost:8083/plan/savePlan.do",jsonMsg,function (data) {
            if(data.code === 1){
              self.dialogValue = "保存成功";
            }else{
              self.dialogValue = "保存失败，错误码：" + data.code;
            }
            self.centerDialogVisible2 = true;
          },"json");
          window.location.reload();
        },
        // submitCancel{
        //
        // },
        erweima(index,row){
          this.centerDialogVisible4 =true;
          this.creatQrCode();
        },
        creatQrCode() {
          var qrcode = new QRCode(this.$refs.qrCodeUrl, {
            text: '55555', // 需要转换为二维码的内容
            width: 100,
            height: 100,
            colorDark: '#000000',
            colorLight: '#ffffff',
            correctLevel: QRCode.CorrectLevel.H
          })
        },
        goQRcode(goodsId){
          // console.log("woaoaoaoaoaoao"+goodsId);
          this.$router.push({name:"qrcodeee",query:{goodsId:goodsId}})
        },
      }

    }
</script>

<style scoped>
  .personOldDiv{
    width: 80%;
    margin: 0 auto;
    padding-top: 50px;
  }
</style>
