import React from 'react';
import { Layout, Timeline, Card } from "antd";

const { Sider } = Layout;

const AppSidebar = ({priceMap}) => {

    let items = [];
    var sortedMap = new Map([...priceMap.entries()].sort());
    sortedMap.forEach((price, time) => {
        items.push(<Timeline.Item key={time} label={time}>{price}</Timeline.Item>)
    })
    return (
        <Sider width={400} theme="light" >
            <Card title="Price List" headStyle={{background: "#91d5ff"}} className="card">
                <Timeline mode="left">
                    {items}
            </Timeline>
            </Card>
        </Sider>
    );
}

export default AppSidebar;