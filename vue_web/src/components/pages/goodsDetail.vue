<template>
  <div>
    <index-header></index-header>
  <div class="goodsDetail" id="goodsId" align="center">

    <el-card shadow="always" style="width: 50%;margin: 0 auto;padding-top: 50px;margin-bottom: 40px;">
      <h2 style="float: left">农产品详情：</h2>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <el-carousel direction="horizontal" :autoplay="true" style="width: 365px">
        <!--          商品图片源于淘宝，淘宝采用418 * 418 的商品预览图-->
        <el-carousel-item v-for="(v,k) in goodsInfoImg" :key="k" style="width: 365px;height: 300px" >
          <img :src="v" alt="请检查网络连接" width="90%">
        </el-carousel-item>
      </el-carousel>
          <el-button type="primary" @click="addToShopCar" style="margin-top: 30px;">加入购物车</el-button>
      <br/>
      <br/>
      <br/>
      <br/>
      <h2 style="float: left"><img src="https://i.loli.net/2019/12/17/NhTJjayS6ZCsHWP.png" alt="￥" width="30px">：{{ goodsInfoPrice }}</h2>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <p style="font-size: 15px;float: left;font-weight: bold">名称：{{goodsInfoName}}</p>
      <br/>
      <br/>
      <br/>
      <br/>
      <p style="font-size: 15px;float: left;"> 产地：{{ goodsInfoOrigin }}</p>
      <br/>
      <br/>
      <br/>
      <br/>
      <p style="font-size: 15px;float: left;">类型：{{ goodsInfoType }}</p>
      <br/>
      <br/>
      <br/>
      <br/>
      <p style="font-size: 15px;float: left;">预计成熟日期：{{ goodsInfoDate }}</p>
      <br/>
      <br/>
      <br/>
      <br/>
      <p style="font-size: 15px;float: left;">描述：{{ goodsInfoDscrip }}</p>
      <br/>
      <br/>
      <br/>
      <br/>
      <p style="font-size: 15px;float: left;">评价：</p>
      <el-table
        :data="tableData"
        style="width: 100%">
        <!--<el-table-column-->
        <!--prop="date"-->
        <!--label="日期"-->
        <!--width="180">-->
        <!--</el-table-column>-->
        <el-table-column
          prop="userId"
          label="用户名"
          width="80">
        </el-table-column>
        <el-table-column
          prop="value"
          label="评价">
        </el-table-column>
      </el-table>

    </el-card>
    <!--      加入购物车结果提示框-->
    <el-dialog
      title="提示"
      :visible.sync="centerDialogVisible2"
      width="30%"
      center>
      <div style="text-align: center">
        <h4>{{ dialogValue }}</h4>
        <span slot="footer" class="dialog-footer">
          <el-row style="margin-top: 30px;">
            <el-col :span="6" :offset="5">
              <el-button type="primary" @click="clickButton" style="width: 100%">确 定</el-button>
            </el-col>
            <el-col :span="6" :offset="2">
              <el-button type="primary" @click="clickGoToShopCar" :disabled="goToShopCar">前往购物车</el-button>
            </el-col>
          </el-row>
        </span>
      </div>
    </el-dialog>
  </div>
    <index-footer></index-footer>
  </div>
</template>

<script>
  import IndexHeader from './index-header'
  import IndexFooter from './index-footer'
  import moment from "moment"

    export default {
        name: "goodsDetail",
      components: {IndexFooter, IndexHeader},
      data(){
          return{
            goodsInfoId:"",
            goodsInfoImg:{},
            goodsInfoName:"",
            goodsInfoDscrip:"",
            goodsInfoPrice: 0,
            goodsInfoOrigin:"",
            goodsInfoType:"",
            goodsInfoDate:new Date(),
            goodsId:"",
            tableData:[],
            dialogValue: "",
            goToShopCar:true,
            centerDialogVisible2:false,
          };
      },
      mounted() {

      },
      created() {
          //接受goodsId
        this.goodsId = this.$route.query.goodsId;
        console.log("getgoodsId:"+this.goodsId);
        let self = this;
        let jsonObj = {};
        jsonObj.goodsId = this.goodsId;
        let jsonMsg = JSON.stringify(jsonObj);
        $.get("http://localhost:8083/goods/getGoodsById.do",jsonObj,function (data) {
          self.goodsInfoName = data.name;
          self.goodsInfoPrice = data.price;
          self.goodsInfoDscrip = data.dscrip;
          self.goodsInfoOrigin = data.origin;
          if(data.type === 1){
            self.goodsInfoType = '果蔬';
          }else if(data.type ===2){
            self.goodsInfoType = '肉类';
          }else if(data.type ===3){
            self.goodsInfoType = '农副产品';
          }
          else if(data.type ===4){
            self.goodsInfoType = '作物';
          }
          else if(data.type ===5){
            self.goodsInfoType = '其他';
          }
          self.goodsInfoDate = moment(data.date).format("YYYY-MM-DD");
          console.log("date::"+data.date);
          $.get("http://localhost:8083/goods/getGoodsImgMap.do",jsonObj,function (data) {
            self.goodsInfoImg = data;
          },"json");
        },"json");
        $.get("http://localhost:8083/feedback/getFeedbacks.do",jsonObj,function (data) {
          self.tableData = data;
        },"json");
      },
      methods:{
        addToShopCar(){
          let jsonObj = {};
          jsonObj.userId = window.sessionStorage.getItem("userId");
          jsonObj.goodsId = this.goodsId;
          let jsonMsg = JSON.stringify(jsonObj);
          let self = this;
          $.post("http://localhost:8083/shopCar/addOneToShopCar.do",jsonMsg,function (data) {
            if(data.code === 1){
              self.dialogValue = "加入购物车成功";
              self.goToShopCar = false;
            }else{
              self.dialogValue = "加入购物车失败，错误码：" + data.code;
              self.goToShopCar = true;
            }
          },"json");
          this.centerDialogVisible2 = true;
        },
        //点击跳转购物车按钮
        clickGoToShopCar(){
          this.clickButton();
          this.$router.push('shopCar');
        },
        clickButton(){
          this.centerDialogVisible2 = false;
        },
      }


    }
</script>

<style scoped>

</style>
