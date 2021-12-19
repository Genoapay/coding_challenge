import React from 'react';
import {  Layout, Card } from 'antd';

import AppHeader from './components/AppHeader';
import AppSidebar from './components/AppSidebar';
import PricePicker from './components/PricePicker';
import ProfitResult from './components/ProfitResult';

import 'antd/dist/antd.css';
import './App.css';
import { useState } from 'react';

const { Content } = Layout;

function App() {
  const [priceMap, updatePriceMap] = useState(new Map());
  const [profit, setProfit] = useState({});

  const addPrice = (time, price) => {
    var pm = new Map(priceMap);
    pm.set(time, price);
    updatePriceMap(pm);
  };

  return (
     <Layout style={{height: "100%", background: "#fff"}} >
      <AppHeader />
      <Layout>
        <AppSidebar priceMap={priceMap}/>
        <Content style={{background: "#fff"}}>
          <Card title="Price Picker" headStyle={{background: "#91d5ff"}} className="card">
            <PricePicker addPrice={addPrice} updateTimeline={updatePriceMap} priceMap={priceMap} updateProfit={setProfit}/>
          </Card>
          <Card title="Result" headStyle={{background: "#91d5ff"}} className="card">
            <ProfitResult profit={profit}/>
          </Card>
        </Content>
      </Layout>
    </Layout>
  );
}

export default App;
