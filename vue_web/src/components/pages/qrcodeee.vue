<template>

  <div class="qrcode" ref="qrCodeUrl" style="margin-left: 750px;margin-top: 250px;font-size: 20px" >农产品二维码</div>
</template>

<script>

  import QRCode from 'qrcodejs2'
    export default {
        name: "qrcodeee",
     data(){
          return{
            goodsId:"",
          };
     },
       mounted() {
         this.goodsId = this.$route.query.goodsId;
         // console.log("111111");
         //  console.log(this.goodsId);
          this.creatQrCode(this.goodsId);
       },
      methods:{
        creatQrCode(text1) {
          console.log("texe1:"+text1);
          var qrcode = new QRCode(this.$refs.qrCodeUrl, {
            text: text1, // 需要转换为二维码的内容
            width: 100,
            height: 100,
            colorDark: '#000000',
            colorLight: '#ffffff',
            correctLevel: QRCode.CorrectLevel.H
          })
        },
      }
    }
</script>

<style scoped>

</style>
