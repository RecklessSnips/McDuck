<template>
  <!-- 不知道如何布局了，
        首先调宽高是否占满父容器，其次
        加个背景色调试
        style="background-color: red" -->
  <div class="loginHeader mt-0 mb-2">
    <span @click="goHome" class="homeIcon ms-5">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        xmlns:xlink="http://www.w3.org/1999/xlink"
        version="1.1"
        width="58"
        height="58"
        viewBox="380 410 540 100"
        xml:space="preserve"
      >
        <desc>Created with Fabric.js 5.3.0</desc>
        <defs></defs>
        <g transform="matrix(1 0 0 1 640 512)" id="background-logo">
          <circle
            style="
              stroke: none;
              stroke-width: 0;
              stroke-dasharray: none;
              stroke-linecap: butt;
              stroke-dashoffset: 0;
              stroke-linejoin: miter;
              stroke-miterlimit: 4;
              fill: rgb(255, 255, 255);
              fill-rule: nonzero;
              opacity: 1;
            "
            paint-order="stroke"
            x="-640"
            y="-512"
            rx="0"
            ry="0"
            width="1280"
            height="1024"
          />
        </g>
        <g
          transform="matrix(1.9211538461538462 0 0 1.9211538461538462 640 391.6222708804826)"
          id="logo-logo"
        >
          <g style="" paint-order="stroke">
            <g
              transform="matrix(0.16980763392487497 0 0 -0.169807633924875 -9.835585892724339 74.85612945546873)"
            >
              <path
                style="
                  stroke: none;
                  stroke-width: 1;
                  stroke-dasharray: none;
                  stroke-linecap: butt;
                  stroke-dashoffset: 0;
                  stroke-linejoin: miter;
                  stroke-miterlimit: 4;
                  fill: rgb(228, 169, 35);
                  fill-rule: nonzero;
                  opacity: 1;
                "
                paint-order="stroke"
                transform=" translate(-544.4449999999999, -102.166)"
                d="M 583.379 102.16 C 583.379 80.69919999999999 565.918 63.2305 544.4490000000001 63.2305 C 522.988 63.2305 505.5200000000001 80.69919999999999 505.5200000000001 102.16 C 505.5200000000001 123.625 522.988 141.09 544.4490000000001 141.09 C 565.9180000000001 141.09 583.379 123.625 583.379 102.16 z M 544.4490000000001 204.332 C 488.113 204.332 442.281 158.5 442.281 102.16 C 442.281 45.8281 488.113 0 544.449 0 C 600.785 0 646.6089999999999 45.8281 646.6089999999999 102.16 C 646.6089999999999 158.5 600.785 204.332 544.449 204.332"
                stroke-linecap="round"
              />
            </g>
            <g
              transform="matrix(0.16980763392487497 0 0 -0.169807633924875 48.44562041533726 74.85612945546873)"
            >
              <path
                style="
                  stroke: none;
                  stroke-width: 1;
                  stroke-dasharray: none;
                  stroke-linecap: butt;
                  stroke-dashoffset: 0;
                  stroke-linejoin: miter;
                  stroke-miterlimit: 4;
                  fill: rgb(228, 169, 35);
                  fill-rule: nonzero;
                  opacity: 1;
                "
                paint-order="stroke"
                transform=" translate(-887.664, -102.166)"
                d="M 926.602 102.16 C 926.602 80.69919999999999 909.141 63.2305 887.6759999999999 63.2305 C 866.199 63.2305 848.7339999999999 80.69919999999999 848.7339999999999 102.16 C 848.7339999999999 123.625 866.199 141.09 887.6759999999999 141.09 C 909.141 141.09 926.602 123.625 926.602 102.16 z M 887.676 204.332 C 831.336 204.332 785.492 158.5 785.492 102.16 C 785.492 45.8281 831.336 0 887.676 0 C 944.004 0 989.836 45.8281 989.836 102.16 C 989.836 158.5 944.004 204.332 887.676 204.332"
                stroke-linecap="round"
              />
            </g>
            <g transform="matrix(0.16980763392487497 0 0 -0.169807633924875 0 -32.30293072061377)">
              <path
                style="
                  stroke: none;
                  stroke-width: 1;
                  stroke-dasharray: none;
                  stroke-linecap: butt;
                  stroke-dashoffset: 0;
                  stroke-linejoin: miter;
                  stroke-miterlimit: 4;
                  fill: rgb(228, 169, 35);
                  fill-rule: nonzero;
                  opacity: 1;
                "
                paint-order="stroke"
                transform=" translate(-602.366930041589, -733.2275)"
                d="M 1181.85 941.941 C 1157.1299999999999 977.6840000000001 1115.09 997.465 1071.6299999999999 997.465 L 762.91 997.465 C 729.492 997.465 705.7769999999999 964.895 716.063 933.0980000000001 C 733.27 879.883 755.012 812.801 755.707 811.4060000000001 C 764.438 793.922 782.297 782.609 802.309 782.609 L 899.2149999999999 782.609 L 820.816 725.652 C 802.535 712.371 794.887 688.828 801.867 667.34 L 831.813 575.184 L 753.4259999999999 632.137 C 742.2299999999999 640.289 728.4609999999999 643.6289999999999 714.414 641.453 C 705.816 640.125 697.789 636.273 690.754 631.16 L 613.723 575.1909999999999 L 643.668 667.3549999999999 C 650.648 688.8439999999999 643 712.3789999999999 624.723 725.6519999999999 L 546.3199999999999 782.6089999999999 L 643.223 782.6089999999999 C 665.9219999999999 782.6089999999999 685.867 797.117 692.8159999999999 818.645 L 697.4999999999999 832.8009999999999 C 700.4569999999999 841.7339999999999 700.4139999999999 851.387 697.3829999999999 860.2929999999999 L 675.6479999999999 924.0699999999999 C 672.9529999999999 931.977 661.7379999999999 931.887 659.1719999999999 923.938 L 634.328 846.992 L 511.102 846.992 C 489.719 846.992 469.27 835.223 460.816 815.582 C 451.03099999999995 792.836 458.36699999999996 766.934 478.01599999999996 752.652 L 579.691 678.789 L 540.852 559.277 C 535.625 543.1880000000001 538.273 526.195 548.1129999999999 512.648 C 564.5079999999999 490.098 598.285 484.418 621.1089999999999 500.96900000000005 L 722.7579999999999 574.836 L 824.4259999999999 500.969 C 833.4799999999999 494.39799999999997 844.059 490.918 855.012 490.926 C 871.6909999999999 490.926 887.535 499.051 897.4219999999999 512.656 C 907.262 526.1949999999999 909.9019999999999 543.188 904.6839999999999 559.2769999999999 L 865.8439999999998 678.789 L 965.5659999999998 751.2379999999999 C 982.8439999999998 763.7929999999999 992.4379999999998 785.2929999999999 987.7729999999998 806.1289999999999 C 982.3549999999998 830.3199999999999 961.1799999999998 846.992 936.8589999999998 846.992 L 811.191 846.992 L 787.1410000000001 921.0699999999999 C 785.219 926.9999999999999 789.6370000000001 933.0819999999999 795.8710000000001 933.0819999999999 L 1073.45 933.0819999999999 C 1079.93 933.0819999999999 1086.45 932.3129999999999 1092.66 930.4839999999999 C 1134.18 918.2699999999999 1146.8400000000001 879.492 1136.39 846.867 L 1021.87 489.438 C 1013.36 462.871 988.652 444.84799999999996 960.754 444.84799999999996 L 490.359 444.84799999999996 C 461.12899999999996 444.84799999999996 435.59 464.602 428.25 492.89799999999997 L 299.363 989.715 C 284.668 1046.4 233.496 1085.99 174.93 1085.99 L 34.0273 1085.99 C 17.168 1085.99 0.917969 1072.35 0.0429688 1055.52 C -0.917969 1036.96 13.8438 1021.61 32.1914 1021.61 L 174.93 1021.61 C 204.16400000000002 1021.61 229.707 1001.85 237.03900000000002 973.551 L 365.922 476.738 C 380.637 420.051 431.80100000000004 380.46500000000003 490.35900000000004 380.46500000000003 L 960.75 380.46500000000003 C 1016.64 380.46500000000003 1066.12 416.57000000000005 1083.17 469.78900000000004 L 1198.48 829.695 C 1210.81 868.168 1204.73 908.883 1181.85 941.941"
                stroke-linecap="round"
              />
            </g>
            <g
              transform="matrix(0.16980763392487497 0 0 -0.169807633924875 -83.09524340670333 -67.1630795043192)"
            >
              <path
                style="
                  stroke: none;
                  stroke-width: 1;
                  stroke-dasharray: none;
                  stroke-linecap: butt;
                  stroke-dashoffset: 0;
                  stroke-linejoin: miter;
                  stroke-miterlimit: 4;
                  fill: rgb(228, 169, 35);
                  fill-rule: nonzero;
                  opacity: 1;
                "
                paint-order="stroke"
                transform=" translate(-113.01765, -938.5195)"
                d="M 159.816 906.328 L 66.2188 906.328 C 48.4414 906.328 34.027300000000004 920.742 34.027300000000004 938.52 L 34.027300000000004 938.52 C 34.027300000000004 956.297 48.4414 970.711 66.2188 970.711 L 159.816 970.711 C 177.598 970.711 192.008 956.297 192.008 938.52 L 192.008 938.52 C 192.008 920.742 177.598 906.328 159.816 906.328"
                stroke-linecap="round"
              />
            </g>
            <g
              transform="matrix(0.16980763392487497 0 0 -0.169807633924875 19.703980297212922 42.161792138658114)"
            >
              <path
                style="
                  stroke: none;
                  stroke-width: 1;
                  stroke-dasharray: none;
                  stroke-linecap: butt;
                  stroke-dashoffset: 0;
                  stroke-linejoin: miter;
                  stroke-miterlimit: 4;
                  fill: rgb(228, 169, 35);
                  fill-rule: nonzero;
                  opacity: 1;
                "
                paint-order="stroke"
                transform=" translate(-718.404, -294.70349999999996)"
                d="M 1019.95 262.512 L 416.859 262.512 C 399.082 262.512 384.668 276.926 384.668 294.703 L 384.668 294.703 C 384.668 312.484 399.082 326.895 416.85900000000004 326.895 L 1019.95 326.895 C 1037.73 326.895 1052.14 312.484 1052.14 294.703 L 1052.14 294.703 C 1052.14 276.926 1037.73 262.51199999999994 1019.95 262.51199999999994"
                stroke-linecap="round"
              />
            </g>
          </g>
        </g>
        <g
          transform="matrix(1.9211538461538462 0 0 1.9211538461538462 656.7490191656568 661.6656114796616)"
          id="text-logo"
        >
          <g style="" paint-order="stroke">
            <g transform="matrix(1 0 0 1 0 0)" id="text-logo-path-0">
              <path
                style="
                  stroke: none;
                  stroke-width: 0;
                  stroke-dasharray: none;
                  stroke-linecap: butt;
                  stroke-dashoffset: 0;
                  stroke-linejoin: miter;
                  stroke-miterlimit: 4;
                  fill: rgb(228, 169, 35);
                  fill-rule: nonzero;
                  opacity: 1;
                "
                paint-order="stroke"
                transform=" translate(-114.12615843869939, 25.315)"
                d="M 14.17 0 C 15.3 0 16.5 -0.97 16.8 -2.17 L 19.27 -18.82 L 19.27 -2.7 C 19.27 -1.2 20.47 0 21.97 0 L 24.07 0 C 25.57 0 26.77 -1.2 26.77 -2.7 L 26.77 -47.85 C 26.77 -49.35 25.57 -50.55 24.07 -50.55 L 21.82 -50.55 C 20.7 -50.55 19.43 -49.57 19.2 -48.45 L 13.35 -18.22 L 7.42 -48.45 C 7.2 -49.57 6 -50.55 4.88 -50.55 L 2.63 -50.55 C 1.13 -50.55 -0.07 -49.35 -0.07 -47.85 L -0.07 -2.7 C -0.07 -1.2 1.13 0 2.63 0 L 4.72 0 C 6.15 0 7.42 -1.2 7.42 -2.7 L 7.42 -18.97 L 9.97 -2.1 C 10.2 -0.97 11.4 0 12.53 0 Z M 43.05 -14.7 C 43.05 -8.78 44.7 -5.33 47.02 -3 C 49.5 -0.6 52.58 0 55.42 0 C 58.13 0 61.27 -0.6 63.75 -3 C 66.08 -5.33 67.65 -8.78 67.65 -14.7 L 67.65 -15.75 C 67.65 -17.25 66.45 -18.45 65.03 -18.45 L 62.85 -18.45 C 61.35 -18.45 60.15 -17.25 60.15 -15.75 L 60.15 -14.7 C 60.15 -12 59.77 -9.97 58.88 -8.78 C 58.2 -8.03 56.85 -7.42 55.42 -7.42 C 52.2 -7.42 50.63 -9.67 50.63 -14.7 L 50.63 -35.92 C 50.63 -40.95 52.27 -43.13 55.42 -43.13 C 58.5 -43.13 60.15 -40.95 60.15 -35.92 L 60.15 -34.88 C 60.15 -33.38 61.35 -32.17 62.85 -32.17 L 65.03 -32.17 C 66.45 -32.17 67.65 -33.38 67.65 -34.88 L 67.65 -35.92 C 67.65 -41.63 66.08 -45.38 63.67 -47.63 C 61.2 -49.88 58.2 -50.55 55.42 -50.55 C 52.58 -50.55 49.58 -49.88 47.1 -47.63 C 44.7 -45.38 43.05 -41.63 43.05 -35.92 Z M 93.15 0 C 102.15 0 106.35 -5.1 106.35 -14.85 L 106.35 -35.7 C 106.35 -45.45 102.15 -50.55 93.15 -50.55 L 85.5 -50.55 C 84 -50.55 82.8 -49.35 82.8 -47.85 L 82.8 -2.7 C 82.8 -1.2 84 0 85.5 0 Z M 93.15 -43.13 C 97.13 -43.13 98.78 -40.73 98.78 -35.7 L 98.78 -14.85 C 98.78 -9.82 97.13 -7.42 93.15 -7.42 L 90.23 -7.42 L 90.23 -43.13 Z M 121.88 -13.8 C 121.88 -5.85 125.17 -0.07 134.18 -0.07 C 142.88 -0.07 146.47 -5.4 146.47 -13.8 L 146.47 -47.92 C 146.47 -49.42 145.28 -50.63 143.78 -50.63 L 141.68 -50.63 C 140.25 -50.63 139.05 -49.42 139.05 -47.92 L 139.05 -13.8 C 139.05 -10.2 138 -7.5 134.18 -7.5 C 130.5 -7.5 129.3 -9.82 129.3 -13.8 L 129.3 -47.92 C 129.3 -49.42 128.1 -50.63 126.6 -50.63 L 124.5 -50.63 C 123.08 -50.63 121.88 -49.42 121.88 -47.92 Z M 162.83 -14.7 C 162.83 -8.78 164.47 -5.33 166.8 -3 C 169.28 -0.6 172.35 0 175.2 0 C 177.9 0 181.05 -0.6 183.53 -3 C 185.85 -5.33 187.43 -8.78 187.43 -14.7 L 187.43 -15.75 C 187.43 -17.25 186.22 -18.45 184.8 -18.45 L 182.63 -18.45 C 181.13 -18.45 179.93 -17.25 179.93 -15.75 L 179.93 -14.7 C 179.93 -12 179.55 -9.97 178.65 -8.78 C 177.97 -8.03 176.63 -7.42 175.2 -7.42 C 171.97 -7.42 170.4 -9.67 170.4 -14.7 L 170.4 -35.92 C 170.4 -40.95 172.05 -43.13 175.2 -43.13 C 178.28 -43.13 179.93 -40.95 179.93 -35.92 L 179.93 -34.88 C 179.93 -33.38 181.13 -32.17 182.63 -32.17 L 184.8 -32.17 C 186.22 -32.17 187.43 -33.38 187.43 -34.88 L 187.43 -35.92 C 187.43 -41.63 185.85 -45.38 183.45 -47.63 C 180.97 -49.88 177.97 -50.55 175.2 -50.55 C 172.35 -50.55 169.35 -49.88 166.88 -47.63 C 164.47 -45.38 162.83 -41.63 162.83 -35.92 Z M 209.85 -31.65 L 209.85 -47.85 C 209.85 -49.35 208.65 -50.55 207.15 -50.55 L 205.05 -50.55 C 203.55 -50.55 202.35 -49.35 202.35 -47.85 L 202.35 -2.7 C 202.35 -1.2 203.55 0 205.05 0 L 207.15 0 C 208.65 0 209.85 -1.2 209.85 -2.7 L 209.85 -17.55 L 210.75 -18.9 L 220.8 -1.35 C 221.17 -0.67 222.38 0 223.13 0 L 225.53 0 C 227.78 0 228.97 -2.1 227.92 -4.05 L 215.32 -26.25 L 227.85 -46.42 C 229.05 -48.38 227.85 -50.55 225.53 -50.55 L 222.97 -50.55 C 222.22 -50.55 221.1 -49.88 220.72 -49.27 Z"
                stroke-linecap="round"
              />
            </g>
          </g>
        </g>
        <g
          transform="matrix(1 0 0 1 325 406.16857822036934)"
          id="tagline-8ca46d32-2fbe-4c06-8d48-3853754ea96f-logo"
        >
          <g style="" paint-order="stroke">
            <g
              transform="matrix(1 0 0 1 0 0)"
              id="tagline-8ca46d32-2fbe-4c06-8d48-3853754ea96f-logo-path-0"
            >
              <path
                style="
                  stroke: none;
                  stroke-width: 0;
                  stroke-dasharray: none;
                  stroke-linecap: butt;
                  stroke-dashoffset: 0;
                  stroke-linejoin: miter;
                  stroke-miterlimit: 4;
                  fill: rgb(229, 174, 44);
                  fill-rule: nonzero;
                  opacity: 1;
                "
                paint-order="stroke"
                transform=" translate(0, 0)"
                d=""
                stroke-linecap="round"
              />
            </g>
          </g>
        </g>
      </svg>
    </span>
  </div>
  <Button class="btn btn-outline-success" label="Back" icon="pi pi-arrow-left" @click="back" />
  <div
    class="d-flex flex-column justify-content-center align-items-center"
    style="background-color:; height: 70vh"
  >
    <div class="mt-3">
      <h1 class="text-center">Start your business journey with us!</h1>
      <h5 class="text-center">I made it by being</h5>
      <h5 class="text-center">Tougher than the Toughies</h5>
      <h5 class="text-center">Smarter than the Smarties</h5>
      <h5 class="text-center">And I made it square!</h5>
    </div>
    <div
      class="d-flex justify-content-center align-items-start mt-3"
      style="height: 50vh; width: 90vw"
    >
      <!-- 这个div是为了保持d-flex的形状 -->
      <div>
        <form>
          <div class="mt-2 mb-4">
            <FloatLabel>
              <InputText
                id="username"
                v-model="username"
                size="small"
                type="text"
                variant="filled"
                :pt="{
                  root: {
                    style: 'width: 234px'
                  }
                }"
              />
              <label for="username">Username</label>
            </FloatLabel>
          </div>
          <div class="mt-2 mb-4">
            <FloatLabel>
              <InputText
                id="email"
                v-model="email"
                size="small"
                type="text"
                variant="filled"
                :pt="{
                  root: {
                    style: 'width: 234px'
                  }
                }"
              />
              <label for="email">Email</label>
            </FloatLabel>
          </div>
          <div class="mb-4">
            <FloatLabel>
              <Password id="password" v-model="password" :feedback="false" toggleMask />
              <label for="password">Password</label>
            </FloatLabel>
          </div>
          <div class="d-flex justify-content-center">
            <Button
              label="Submit"
              icon="pi pi-user-plus"
              severity="success"
              class="btn btn-outline-primary"
              @click="register"
            ></Button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: 'Register'
}
</script>

<script lang="ts" setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const URL = 'http://localhost:8080'

const router = useRouter()

const back = () => {
  router.replace('/login')
}

const goHome = () => {
  router.push('/')
}

let username = ref(null)
let email = ref(null)
let password = ref(null)

const register = () => {
  let user = JSON.stringify({
    nickName: username.value,
    password: password.value,
    firstName: null,
    lastName: null,
    fullName: null,
    address: null,
    phoneNumber: null,
    email: email.value,
    creditCard: null,
    balance: null,
    registeredTime: new Date().toISOString()
  })

  fetch(`${URL}/api/register`, {
    method: 'POST',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json'
    },
    body: user
  })
    .then((response) => {
      return response.json()
    })
    .then((ifRegistered) => {
      // TODO: 如果成功登陆，等待两秒传送到主页。这期间用vue的toast来提醒用户！
      if (ifRegistered) {
        setTimeout(() => {
          router.push('/home')
        }, 2000)
      }
      console.log('If successfully registered: ' + ifRegistered)
    })
    .catch((error) => {
      console.log(error)
    })
}
</script>

<style scoped>
.divider {
  height: 200px;
  margin: 0 30px;
}

.homeIcon {
  cursor: pointer;
}

.loginHeader {
  background-color: antiquewhite;
}
</style>
