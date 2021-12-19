import React from 'react';
import { List, Card } from "antd";

const ProfitResult = ({profit}) => {
  const data = [
    {
      title: 'Max Profit',
      content: profit  ? profit.maxProfit : null
    },
    {
      title: 'Buy Value',
      content: profit  ? profit.buyValue : null
    },
    {
      title: 'Sell Value',
      content: profit  ? profit.sellValue: null
    },
  ];
    return (
        <List
            grid={{
            gutter: 16,
            xs: 1,
            sm: 2,
            md: 4,
            lg: 4,
            xl: 6,
            xxl: 3,
            }}
            dataSource={data}
            renderItem={item => (
            <List.Item>
                <Card title={item.title}>{item.content}</Card>
            </List.Item>
            )}
        />
    );
};

export default ProfitResult;

