<template>
  <div>
<!--    <Header/>-->
<!--    <div style="display: flex">-->
<!--      <vue-particles-->
<!--          color="#555"-->
<!--          :particleOpacity="0.7"-->
<!--          :particlesNumber="150"-->
<!--          shapeType="circle"-->
<!--          :particleSize="4"-->
<!--          linesColor="#555"-->
<!--          :linesWidth="1"-->
<!--          :lineLinked="true"-->
<!--          :lineOpacity="0.4"-->
<!--          :linesDistance="150"-->
<!--          :moveSpeed="2"-->
<!--          :hoverEffect="true"-->
<!--          hoverMode="grab"-->
<!--          :clickEffect="false"-->
<!--          clickMode="push"-->
<!--          class="lizi"-->
<!--      />-->
<!--      <Aside/>-->
      <router-view style="flex: 1"></router-view>
    </div>
<!--  </div>-->
</template>

<script>


import Header from "./components/Header";
import Aside from "./components/Aside";

export default {
  name: 'Home',
  components: {
    Header,
    Aside
  }
}

</script>
<style>
  body{
    width: 100%;
    background-color: rgb(239,239,239);
  }
  .lizi{
    position: fixed;
    top: 0;
    width: 100%;
  }

</style>
